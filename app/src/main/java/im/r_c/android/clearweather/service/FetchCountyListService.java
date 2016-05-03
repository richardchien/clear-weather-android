package im.r_c.android.clearweather.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import im.r_c.android.clearweather.db.CountyDAO;
import im.r_c.android.clearweather.model.Consts;
import im.r_c.android.clearweather.model.County;
import im.r_c.android.clearweather.util.L;

/**
 * ClearWeather
 * Created by richard on 16/5/2.
 */
public class FetchCountyListService extends IntentService {
    private static final String TAG = "FetchCountyListService";

    public static final int RESULT_OK = 1;
    public static final int RESULT_ERROR = 2;

    public static void start(Context context) {
        Intent starter = new Intent(context, FetchCountyListService.class);
        context.startService(starter);
    }

    public FetchCountyListService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        L.v(TAG, "onHandleIntent");

        if (!shouldFetchCountyList()) {
            postToAddActivity(RESULT_OK);
            return;
        }

//        String countyListJson = HttpUtils.getSync(Consts.API_COUNTY_LIST);

        // Using local file instead of fetching from network
        StringBuilder stringBuilder = new StringBuilder();
        String countyListJson = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(Consts.CITY_LIST_FILENAME)));
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            countyListJson = stringBuilder.toString();
            L.d(TAG, countyListJson);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<County> countyList = new LinkedList<>();
        try {
            // Parse JSON array
            JSONArray jsonArray = new JSONArray(countyListJson);
            for (int i = 0; i < jsonArray.length(); i++) {
                County county = new County();
                JSONObject object = (JSONObject) jsonArray.get(i);
                county.setName(object.getString(County.KEY_NAME));
                county.setNameEn(object.getString(County.KEY_NAME_EN));
                county.setCity(object.getString(County.KEY_CITY));
                county.setProvince(object.getString(County.KEY_PROVINCE));
                county.setCode(object.getString(County.KEY_CODE));
                countyList.add(county);
            }

            // Store the countyList into database
            CountyDAO dao = new CountyDAO(getApplicationContext());
            for (County county : countyList) {
                dao.insert(county);
            }

            postToAddActivity(RESULT_OK);
        } catch (Exception e) {
            e.printStackTrace();
            postToAddActivity(RESULT_ERROR);
        }
    }

    private boolean shouldFetchCountyList() {
        File dbFile = getApplicationContext().getDatabasePath(Consts.DATABASE_FILE_NAME);
        L.d(TAG, dbFile.getAbsolutePath() + ", exists: " + dbFile.exists());
        return !dbFile.exists();
    }

    private void postToAddActivity(final int resultCode) {
        L.v(TAG, "Sent post to AddActivity");
        EventBus.getDefault().postSticky(resultCode);
    }
}
