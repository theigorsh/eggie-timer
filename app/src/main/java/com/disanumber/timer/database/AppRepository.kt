package com.disanumber.timer.database

import android.arch.lifecycle.LiveData
import android.content.Context
import com.disanumber.timer.model.TimerEntity
import com.disanumber.timer.util.TimerDataUtil
import java.util.concurrent.Executors


class AppRepository private constructor(context: Context) {

    lateinit var timers: LiveData<List<TimerEntity>>
    private val mDb: AppDatabase = AppDatabase.getInstance(context)!!
    private val executor = Executors.newSingleThreadExecutor()

    private fun getAllNotes(): LiveData<List<TimerEntity>> {
        return mDb.timerDao().getAll()
    }

    fun insertTimer(timer: TimerEntity) {
        executor.execute { mDb.timerDao().insertTimer(timer) }
    }

    init {
        timers = getAllNotes()
    }

    fun addTimerData() {
        executor.execute {
            mDb.timerDao().insertAll(TimerDataUtil.getTimers())
        }
    }


    fun update(timer: TimerEntity) {
        executor.execute {
            mDb.timerDao().update(timer)
        }
    }


    fun deleteTimer(id: Int) {
        executor.execute {
            mDb.timerDao().deleteTimer(id)
        }
    }

    companion object {
        private var ourInstance: AppRepository? = null

        fun getInstance(context: Context): AppRepository {
            if (ourInstance == null) {
                ourInstance = AppRepository(context)
            }
            return ourInstance as AppRepository
        }
    }
}

