支持listview和RecyclerView滑到底部自动加载。
--
![](https://github.com/lazy-ape/LoadMoreDemo/raw/master/screenshots/1.gif)  

1.引入库

```
compile 'com.lyape:loadmore:1.0.0'
```

2.布局文件中使用`ListView`和`RecyclerView`的地方替换成`LoadMoreListView`和`LoadMoreRecyclerView`

```xml

<com.lyape.loadmore.LoadMoreRecyclerView
        android:id="@+id/list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"></com.lyape.loadmore.LoadMoreRecyclerView>

```

3.代码中设置`LoadMoreListener`

```java
 list.setLoadMoreListener(new ILoadMoreCallback() {
            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 0 ; i < 10 ; i ++ ){
                            list.add("new data " + i + i);
                        }
                        if(list.size()>=30){
                            mList.loadComplete(false);
                        }else{
                            mList.loadComplete(true);
                        }

                    }
                },2000);
            }
        });
```

4.数据加载完成后调用`loadComplete`通知控件完成加载，`loadComplete`需要传入一个布尔值，代表是否还有数据，如果还有数据则传入`true`，如果已无更多数据则传入`false`.