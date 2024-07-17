package hkmu.alin3.greenstar;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferencesUtils {
    private static final String SP= "sharedpreference";
    public SharedPreferencesUtils(){

    }
    private static SharedPreferencesUtils instace = new SharedPreferencesUtils();
    private static SharedPreferences mSp = null;

    public static SharedPreferencesUtils getInstace() {

        if (mSp == null) {
            mSp = MyApplication.getContext().getSharedPreferences(SP, Context.MODE_PRIVATE);
        }
        return instace;
    }

    /**
     * 保存数据
     *
     */
    public void save(String key, Object value) {
        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    // 读取String类型数据
    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    // 读取boolean类型数据
    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    // 读取int类型数据
    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }
}
