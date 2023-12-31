[com.brother.mfc.shenlong.viewmodel](../index.md) / [MainViewModel](./index.md)

# MainViewModel

`class MainViewModel : ViewModel`

メイン画面のViewModel

### Parameters

`dateModel` - 使用するモデル

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MainViewModel(dateModel: `[`SampleModel`](../../com.brother.mfc.shenlong.model/-sample-model/index.md)`)`<br>メイン画面のViewModel |

### Properties

| Name | Summary |
|---|---|
| [data](data.md) | `val data: MutableLiveData<`[`Date`](https://developer.android.com/reference/java/util/Date.html)`>`<br>表示用のデータ |

### Functions

| Name | Summary |
|---|---|
| [refresh](refresh.md) | `fun refresh(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>データを更新する |
