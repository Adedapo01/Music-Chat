package com.codepathgroupeleven.musicchat.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.codepathgroupeleven.musicchat.ApiClient
import com.codepathgroupeleven.musicchat.LoginActivity
import com.codepathgroupeleven.musicchat.R
import com.codepathgroupeleven.musicchat.SessionManager
import com.codepathgroupeleven.musicchat.models.Playlist
import com.codepathgroupeleven.musicchat.models.User
import com.google.gson.Gson
import org.w3c.dom.Text
import retrofit2.HttpException
import java.io.IOException


class ProfileFragment : Fragment() {

    lateinit var logout_button : Button
    lateinit var username : TextView
    lateinit var useremail: TextView
    lateinit var userImage : ImageView
    lateinit var apiClient: ApiClient
    lateinit var sessionManager: SessionManager
    var user = User()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logout_button = view.findViewById(R.id.logout_button)
        useremail = view.findViewById(R.id.useremail)
        userImage = view.findViewById(R.id.userImage)
        username = view.findViewById(R.id.username)
        sessionManager = SessionManager(requireContext())
        apiClient = ApiClient(requireContext())

        getUserProfile()
        logout_button.setOnClickListener {
            logout()
        }

    }

    fun logout(){
        sessionManager.removeAuthToken()
        val intent = Intent(requireContext(), LoginActivity::class.java)
        requireContext().startActivity(intent)
        requireActivity().finish()
        //Toast.makeText(requireContext(), "Logging out",Toast.LENGTH_LONG).show()
    }
    fun getUserProfile(){
        lifecycleScope.launchWhenCreated {

            val response = try{
                apiClient.api.getUserProfile()

            } catch(e: IOException){
                Log.e(TAG, "IOException")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e(TAG, "HttpException")
                return@launchWhenCreated
            }catch (e: Exception){
                Log.e(TAG,"Error: $e")
                return@launchWhenCreated
            }
            Log.i(TAG, "$response")
            if (response.code() == 401) {
                val intent = Intent(requireContext(), LoginActivity::class.java)
                requireContext().startActivity(intent)
                sessionManager.saveAuthToken("")
            }
            if (response.isSuccessful && response.body() != null) {
                Log.i(TAG, "Successful")
                val gson = Gson()
                Log.i(TAG, "User: ${response.body()}")

                var jsonObject = response.body()
                user = User.fromJson(jsonObject)

                useremail.text = user.email
                Glide.with(requireContext()).load(user.userImage).centerCrop().into(userImage)
                username.text = user.name
                /*var items = jsonArray?.asJsonObject
                var it = items?.getAsJsonArray("items")

                allPlaylists.addAll(Playlist.fromJsonArray(it))
                adapter.notifyDataSetChanged()
                Log.i(TAG, "playlist: $allPlaylists")*/
            }
        }
    }
    companion object {
        val TAG = "ProfileFragment"
    }
}