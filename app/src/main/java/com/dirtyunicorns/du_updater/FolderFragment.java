/*
* Copyright (C) 2014 Dirty Unicorns
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.dirtyunicorns.du_updater;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dirtyunicorns.du_updater.Utils.MainUtils;
import com.dirtyunicorns.du_updater.Utils.Vars;

/**
 * Created by mazwoz on 12/18/14.
 */
public class FolderFragment extends ListFragment {

    private String dir;

    public FolderFragment() {
        this.setArguments(new Bundle());
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_folder, null);

        Bundle bundle = getArguments();
        dir = bundle.getString("dir", "");
        TextView tv = (TextView)view.findViewById(R.id.Folder);
        tv.setText(dir);

        Vars.files = MainUtils.getFiles(dir);

        if (Vars.files.length >0) {

            ArrayAdapter<String> downloadsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, Vars.files);
            setListAdapter(downloadsAdapter);
        }

        return  view;
    }

}
