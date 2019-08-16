package com.andreamw96.moviecatalogue.provider

import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.andreamw96.moviecatalogue.data.local.FavoriteDao
import com.andreamw96.moviecatalogue.data.model.fromContentValues
import com.andreamw96.moviecatalogue.utils.favorites_table
import dagger.android.DaggerContentProvider
import javax.inject.Inject

class FavoriteProvider : DaggerContentProvider() {

    @Inject
    lateinit var favoriteDao: FavoriteDao

    private val AUTHORITY = "com.andreamw96.moviecatalogue"
    private val SCHEME = "content"

    val URI_FAVORITE = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(favorites_table)
            .build()

    private val FAVORITE = 1
    private val FAVORITE_TYPE = 2
    private val FAVORITE_ID = 3

    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    init {
        sUriMatcher.addURI(AUTHORITY, favorites_table, FAVORITE)
        sUriMatcher.addURI(AUTHORITY, "$favorites_table/#", FAVORITE_TYPE)
        sUriMatcher.addURI(AUTHORITY, "$favorites_table/detail/#", FAVORITE_ID)
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?, selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val cursor: Cursor?
        when (sUriMatcher.match(uri)) {
            FAVORITE -> {
                cursor = favoriteDao.provideAllFavorites()
            }
            FAVORITE_TYPE -> {
                cursor = favoriteDao.provideFavorites(uri.lastPathSegment!!.toInt() == 1)
            }
            FAVORITE_ID -> {
                cursor = favoriteDao.provideIsFavorite(uri.lastPathSegment!!.toInt())
            }
            else -> {
                cursor = null
            }
        }

        return cursor
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues): Uri? {
        val added = when(sUriMatcher.match(uri)) {
            FAVORITE -> {
                favoriteDao.insertProvider(fromContentValues(values))
                1
            }
            else -> {
                0
            }
        }

        context.contentResolver.notifyChange(uri, null)
        return Uri.parse("$URI_FAVORITE/$added")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return when(sUriMatcher.match(uri)) {
            FAVORITE_ID -> {
                favoriteDao.deleteFavoritesProvider(uri.lastPathSegment!!.toInt())
                context.contentResolver.notifyChange(uri, null)
                1
            }
            else -> {
                context.contentResolver.notifyChange(uri, null)
                0
            }
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}