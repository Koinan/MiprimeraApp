package miprimeraapp.android.teaching.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import miprimeraapp.android.teaching.com.myapplication.fragments.GameDetailFragment;

public class WebViewActivity extends AppCompatActivity {
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar myToolBar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolBar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.leftarrow);

        getSupportActionBar().setTitle("Webview");


        WebView myWebView = findViewById(R.id.web_view);
        myWebView.setWebViewClient(new WebViewClient());

        url = getIntent().getStringExtra("urljuego");
        myWebView.loadUrl(url);


    }

}
