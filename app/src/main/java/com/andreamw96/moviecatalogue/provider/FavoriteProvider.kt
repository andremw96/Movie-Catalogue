package com.andreamw96.moviecatalogue.provider

import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.utils.favorites_table
import dagger.android.DaggerContentProvider
import javax.inject.Inject


class FavoriteProvider : DaggerContentProvider() {

    @Inject
    lateinit var favoriteDao: FavoriteDao

    private val AUTHORITY = "com.andreamw96.moviecatalogue.provider"

    val URI_FAVORITE = Uri.parse("content://" + AUTHORITY + "/" + favorites_table)

    private val FAVORITE = 1
    private val FAVORITE_ID = 2

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        sUriMatcher.addURI(AUTHORITY, favorites_table, FAVORITE)
        sUriMatcher.addURI(AUTHORITY, "$favorites_table/#", FAVORITE_ID)
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor : Cursor?
        when(sUriMatcher.match(uri)) {
            FAVORITE -> {
                cursor = favoriteDao.provideFavorites()
            }
            else -> {
                cursor = null
            }
        }

        return cursor
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}