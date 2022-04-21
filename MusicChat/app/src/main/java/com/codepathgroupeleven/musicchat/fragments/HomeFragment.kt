package com.codepathgroupeleven.musicchat.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepathgroupeleven.musicchat.PlaylistAdapter
import com.codepathgroupeleven.musicchat.R
import com.codepathgroupeleven.musicchat.RetrofitInstance
import com.codepathgroupeleven.musicchat.models.Playlist
import retrofit2.HttpException
import java.io.IOException
import com.google.gson.Gson
import android.content.SharedPreferences





class HomeFragment() : Fragment() {

    var token : String = ""
    lateinit var playlistRecyclerView: RecyclerView
    lateinit var adapter: PlaylistAdapter
    var allPlaylists: MutableList<Playlist> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val sharedPreferences = requireActivity()!!.applicationContext.getSharedPreferences("MY_APP",   Context.MODE_PRIVATE) // kotlin

        token = sharedPreferences.getString("TOKEN","").toString()

        //token = arguments?.getString("token").toString()
        Log.i("Fahmi", "token value: $token")
        //This is where we set up our views and click listeners
        playlistRecyclerView = view.findViewById(R.id.playlistRecyclerView)
        adapter = PlaylistAdapter(requireContext(), allPlaylists)
        playlistRecyclerView.adapter = adapter
        playlistRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        getAllPlaylists()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.search -> {
                // todo
                Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.addToPlaylist -> {
                // TODO
                Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
                true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    fun getAllPlaylists() {

        lifecycleScope.launchWhenCreated {
            Log.i(TAG, "It is calling the function!")
            val response = try{
                RetrofitInstance.api.getAllPlaylists(token = token)

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
            if (response.isSuccessful && response.body() != null) {
                Log.i(TAG, "Successful")
                val gson = Gson()
                Log.i(TAG, "Playlists: ${response.body()}")

                var jsonArray = response.body()
                var items = jsonArray?.asJsonObject
                var it = items?.getAsJsonArray("items")

                allPlaylists.addAll(Playlist.fromJsonArray(it))
                adapter.notifyDataSetChanged()
                Log.i(TAG, "playlist: $allPlaylists")
            }
        }

    }

    companion object{

        private val TAG = "HomeFragment"
    }


}