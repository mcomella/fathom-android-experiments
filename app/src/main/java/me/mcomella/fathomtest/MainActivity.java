package me.mcomella.fathomtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        // TODO: 1) Inject fathom into webview viewing page
        // 2) Figure out if there is a better way to get the source (e.g. XHR). I've had trouble with this in the past.
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
            Log.d("lol", consoleMessage.messageLevel() + "> " + consoleMessage.sourceId() + ":" + consoleMessage.lineNumber() + ": " + consoleMessage.message());
            if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                Toast.makeText(MainActivity.this, "console: " + consoleMessage.message(), Toast.LENGTH_SHORT).show();
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    public class InjectClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            final String script = Util.getStringFromResources(view.getContext(), R.raw.extract);

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
