# HeaderAndFooterRVAdapter
HeaderAndFooterRecyclerViewAdapter

Getting started
---------------

include the lib library.

       compile project(':library')

Screen Shot
-----------
![](https://github.com/liuzhanta/HeaderAndFooterRVAdapter/blob/master/headerAndfooter.gif)

Usage Xml
---------

You can use it simply : Use your adapter to extends [BaseRecyclerViewAdapter](https://github.com/liuzhanta/HeaderAndFooterRVAdapter/blob/master/library/src/main/java/com/zterry/headerandfooteradapter_rv/BaseRecyclerViewAdapter.java)
    
    public class MyAdapter extends BaseRecyclerViewAdapter<String> {
    
        public MyAdapter(Context mContext) {
            super(mContext);
        }
    
        @Override
        protected int getLayoutId() {
            return R.layout.adapter_rv_item;
        }
    
        @Override
        protected void onBindViewData(RecyclerViewHolder holder, String s, int position) {
            holder.setText(R.id.tv_name, s);
        }
    }
    
####add header
    
    mAdapter.addHeader(R.layout.adapter_item_footerr);

####remove header
        
    mAdapter.removeHeader();
        
    
####add footer
     
    mAdapter.addFooter(R.layout.adapter_item_footer);
             
####remove footer
     
    mAdapter.removeFooter();
            


Developed by
------------
Name: ZTerry Liu  
E-mail: tata1989y@gmail.com  
Subject: ItemContainer 
 
License
---------

    Copyright 2016 刘战塔
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
 
      http://www.apache.org/licenses/LICENSE-2.0
 
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.      