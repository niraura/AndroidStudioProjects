/*
 * Copyright 2019 Brother Industries, Ltd.
 */

package com.brother.mfc.shenlong.viewmodel

import androidx.lifecycle.MutableLiveData
import com.brother.mfc.shenlong.model.SampleModel
import java.util.*

/**
 * メイン画面のViewModel
 * @param dateModel 使用するモデル
 */
class MainViewModel(
    private val dateModel: SampleModel
) : BaseViewModel() {
    /**
     * 表示用のデータ
     */
    val data = MutableLiveData<Date>()

    /**
     * 初期化
     */
    init {
        refresh()
    }

    /**
     * データを更新する
     */
    fun refresh() {
        data.postValue(dateModel.getDate())
    }
}