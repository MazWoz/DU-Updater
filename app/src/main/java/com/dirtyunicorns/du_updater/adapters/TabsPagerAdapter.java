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

package com.dirtyunicorns.du_updater.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dirtyunicorns.du_updater.FolderFragment;
import com.dirtyunicorns.du_updater.Utils.Vars;

/**
 * Created by mazwoz on 12/16/14.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {

    private int count;

    public TabsPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        this.count = count;
    }

    @Override
    public Fragment getItem(int index) {

        Fragment frag = null;
        Bundle bundle = new Bundle();
        Vars.position = index;
        frag = new FolderFragment();
        bundle.putString("dir",Vars.dirs[index]);
        frag.setArguments(bundle);

         switch (index) {
           case 0:
               System.out.println("Passing: " + Vars.dirs[index]);
               return frag;
            case 1:
                System.out.println("Passing: " + Vars.dirs[index]);
                return frag;
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return count;
    }
}