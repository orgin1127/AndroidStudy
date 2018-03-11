package kr.pe.theeye.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AsyncTaskExampleActivity extends Activity implements OnClickListener
{
	private Button mButton;
	private ProgressBar mProgress;
	private AsyncTask<Void, Integer, Void> mTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mButton = (Button) findViewById(R.id.button);
        mProgress = (ProgressBar) findViewById(R.id.progress);
        
        mButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v)
	{
		if(mButton.getText().equals("start"))
		{
			mTask = new AsyncTask<Void, Integer, Void>()
			{
		    	private boolean isCanceled = false;
		    	
		    	@Override
		    	protected void onPreExecute()
		    	{
		    		publishProgress(0);
		    		isCanceled = false;
		    	}
		    	
				@Override
				protected Void doInBackground(Void... params)
				{
					for(int i = 1 ; i <= 100 && ! isCanceled ; i++)
					{
						try
						{
							publishProgress(i);
							Thread.sleep(100);
						}
						catch(InterruptedException e)
						{
							e.printStackTrace();
						}
					}
					
					return null;
				}

				@Override
				protected void onProgressUpdate(Integer... progress)
				{
					mProgress.setProgress(progress[0]);
				}
				
				@Override
				protected void onPostExecute(Void result)
				{
					Toast.makeText(AsyncTaskExampleActivity.this, "¿Ï·áµÊ", Toast.LENGTH_SHORT).show();
					mButton.setText("start");
				}
				
				@Override
				protected void onCancelled()
				{
					isCanceled = true;
					publishProgress(0);
					Toast.makeText(AsyncTaskExampleActivity.this, "Ãë¼ÒµÊ", Toast.LENGTH_SHORT).show();
				}
			};
			
			mTask.execute();
			mButton.setText("cancel");
		}
		else if(mButton.getText().equals("cancel"))
		{
			mTask.cancel(false);
			mButton.setText("start");
		}
	}
}