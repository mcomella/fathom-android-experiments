package me.mcomella.fathomtest;

import android.content.Context;
import android.support.annotation.RawRes;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Util {
    private Util() {}

    public static String getStringFromResources(final Context context, @RawRes final int rawResource) {
        final InputStream is = context.getResources().openRawResource(R.raw.redirect);
        try {
            return readStringFromInputStreamAndCloseStream(is, 4048);
        } catch (final IOException e) {
            // TODO: bad.
            throw new IllegalStateException("ain't nobody got time to handle this properly");
        }
    }

    // from https://dxr.mozilla.org/mozilla-central/rev/bd7645928990649c84609d3f531e803c2d41f269/mobile/android/geckoview/src/main/java/org/mozilla/gecko/util/FileUtils.java#145
    // TODO: add license.
    public static String readStringFromInputStreamAndCloseStream(final InputStream inputStream, final int bufferSize) throws IOException {
        if (bufferSize <= 0) {
            // Safe close: it's more important to alert the programmer of
            // their error than to let them catch and continue on their way.
            throw new IllegalArgumentException("Expected buffer size larger than 0. Got: " + bufferSize);
        }

        final StringBuilder stringBuilder = new StringBuilder(bufferSize);
        final InputStreamReader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
        try {
            int charsRead;
            final char[] buffer = new char[bufferSize];
            while ((charsRead = reader.read(buffer, 0, bufferSize)) != -1) {
                stringBuilder.append(buffer, 0, charsRead);
            }
        } finally {
            reader.close();
        }
        return stringBuilder.toString();
    }
}
