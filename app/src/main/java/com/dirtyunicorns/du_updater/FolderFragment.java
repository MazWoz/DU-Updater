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

        ArrayAdapter<String> downloadsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_expandable_list_item_1, Vars.files);
        setListAdapter(downloadsAdapter);

        return  view;
    }

}
