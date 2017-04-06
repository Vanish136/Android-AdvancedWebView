package im.delight.android.examples.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

import static android.content.ContentValues.TAG;

public class MainActivity extends Activity implements AdvancedWebView.Listener
{

	private static final String TEST_PAGE_URL = "http://www.qq.com/";
	private AdvancedWebView mWebView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mWebView = (AdvancedWebView) findViewById(R.id.webview);
		mWebView.setListener(this, this);
		mWebView.setGeolocationEnabled(false);
		mWebView.setMixedContentAllowed(true);
		mWebView.setCookiesEnabled(true);
		mWebView.setThirdPartyCookiesEnabled(true);
		mWebView.setWebViewClient(new WebViewClient()
		{

			@Override
			public void onPageFinished(WebView view, String url)
			{
				Log.i(TAG, "WebViewClient.onPageFinished: url=" + url);
			}

		});
		mWebView.setWebChromeClient(new WebChromeClient()
		{

			@Override
			public void onReceivedTitle(WebView view, String title)
			{
				super.onReceivedTitle(view, title);
				Log.i(TAG, "WebChromeClient.onReceivedTitle: title=" + title);
			}

		});
		mWebView.addHttpHeader("X-Requested-With", "");
		mWebView.loadUrl(TEST_PAGE_URL);
	}

	@SuppressLint("NewApi")
	@Override
	protected void onResume()
	{
		super.onResume();
		mWebView.onResume();
		// ...
	}

	@SuppressLint("NewApi")
	@Override
	protected void onPause()
	{
		mWebView.onPause();
		// ...
		super.onPause();
	}

	@Override
	protected void onDestroy()
	{
		mWebView.onDestroy();
		// ...
		super.onDestroy();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent)
	{
		super.onActivityResult(requestCode, resultCode, intent);
		mWebView.onActivityResult(requestCode, resultCode, intent);
		// ...
	}

	@Override
	public void onBackPressed()
	{
		if (!mWebView.onBackPressed())
		{
			return;
		}
		// ...
		super.onBackPressed();
	}

	@Override
	public void onPageStarted(String url, Bitmap favicon)
	{
		Log.i(TAG, "onPageStarted: url=" + url);
		//		mWebView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onPageFinished(String url)
	{
		Log.i(TAG, "onPageFinished: url=" + url);
		//		mWebView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onPageError(int errorCode, String description, String failingUrl)
	{
		Log.e(TAG, "onPageError: url=" + failingUrl + " errCode=" + errorCode + " desc=" + description);
	}

	@Override
	public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent)
	{
		Toast.makeText(MainActivity.this, "onDownloadRequested(url = " + url + ",  suggestedFilename = " + suggestedFilename + ",  mimeType = " + mimeType + ",  contentLength = " + contentLength + ",  contentDisposition = " + contentDisposition + ",  userAgent = " + userAgent + ")", Toast.LENGTH_LONG).show();
		Log.i(TAG, "onDownloadRequested" +
				"\nurl=" + url + "\nsuggestedFilename=" + suggestedFilename
				+ "\nmimeType=" + mimeType + "\ncontentLength=" + contentLength
				+ "\ncontentDisposition=" + contentDisposition
				+ "\nuserAgent=" + userAgent);
		/*if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
			// download successfully handled
		}
		else {
			// download couldn't be handled because user has disabled download manager app on the device
		}*/
	}

	@Override
	public void onExternalPageRequest(String url)
	{
		Log.i(TAG, "onExternalPageRequest: url=" + url);
	}

}
