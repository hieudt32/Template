
package app.positiveculture.com.data.response.dto;

import com.google.gson.annotations.SerializedName;

public class Viewport {

    @SerializedName("northeast")
    private Northeast mNortheast;
    @SerializedName("southwest")
    private Southwest mSouthwest;

    public Northeast getNortheast() {
        return mNortheast;
    }

    public void setNortheast(Northeast northeast) {
        mNortheast = northeast;
    }

    public Southwest getSouthwest() {
        return mSouthwest;
    }

    public void setSouthwest(Southwest southwest) {
        mSouthwest = southwest;
    }

}
