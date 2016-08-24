package me.mcomella.fathomtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = (WebView) findViewById(R.id.webview);
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new InjectClient());
        webView.setWebChromeClient(new ChromeClient());

        webView.loadUrl("http://apple.com");
    }

    // Log javascript errors.
    public class ChromeClient extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.d("lol", "s: " + consoleMessage.message());
            Toast.makeText(MainActivity.this, "console: " + consoleMessage.message(), Toast.LENGTH_SHORT).show();
            return super.onConsoleMessage(consoleMessage);
        }
    }

    public class InjectClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            final String script = "documenta.location = 'http://kotaku.com'";

            view.evaluateJavascript(script, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                    Log.d("lol", "s: " + s);
                    Toast.makeText(MainActivity.this, "Finished: " + s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
