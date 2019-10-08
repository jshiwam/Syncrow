package android.com.syncrow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.File;

//client ID=287121036961-h5n4cgehrjdebbtgh2v4h66ma1m2hlml.apps.googleusercontent.com
//client secret=wxOK6HszUp4KBvrWKR4ZUl4k
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String folder_main="/Syncrow Folder";

        File f=new File(Environment.getExternalStorageDirectory()+folder_main);
        if(!f.exists()){
            Toast.makeText(MainActivity.this,"New file created",Toast.LENGTH_SHORT).show();
            f.mkdirs();
            Log.d(TAG,f.getAbsolutePath());
            mPath=f.getPath();
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account==null){
            startSignInActivity();
        }
    }

    private void startSignInActivity() {
        Intent signInintent=new Intent(MainActivity.this,SignInActivity.class);
        startActivity(signInintent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
        }

        public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.menu_settings:
                startSettingsIntent();
                break;
            case R.id.menu_help:
                startHelpIntent();
                break;
        }
        return super.onOptionsItemSelected(item);

        }

    private void startHelpIntent() {
        Intent helpIntent=new Intent(getApplicationContext(),HelpActivity.class);
        startActivity(helpIntent);
    }

    private void startSettingsIntent() {
        Intent settingIntent=new Intent(getApplicationContext(),SettingsActivity.class);
        settingIntent.putExtra("FilePath",mPath);
        startActivity(settingIntent);
    }
}
