package tfj.gatehumex.Gt_QiDongQi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import tfj.gatehumex.R;
import tfj.gatehumex.Settings.HideSystemBarBackground;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gt_qidongqilot000);
		HideSystemBarBackground.hideBoth(this);
		
    }
    
    @Override
    public void onBackPressed() {
      
    }
    
}
