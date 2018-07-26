package com.goodrice.goodrice3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.goodrice.zxinglib.CaptureActivity;

public class KeyActivity extends AppCompatActivity {
    private Button btnScan, btnOk;
    private TextView tvBarcode, tvProduct, tvCompany;
    private ImageView imgResult;
    private EditText edtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key);

        btnScan=(Button)findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(KeyActivity.this,CaptureActivity.class);
                startActivityForResult(intent,1001);
            }
        });

        btnOk = (Button)findViewById(R.id.btnOk);
        edtSearch = (EditText)findViewById(R.id.edtSearch);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultString = edtSearch.getText().toString().trim();　
                if (resultString.equals("")) {
                    Toast.makeText(KeyActivity.this, "請輸入條碼數字", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(KeyActivity.this, "qrcode result is "+resultString, Toast.LENGTH_SHORT).show();
                }
                
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && resultCode== Activity.RESULT_OK)
        {
            String result=data.getStringExtra(CaptureActivity.KEY_DATA);
            Toast.makeText(this, "qrcode result is "+result, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.privacy:
                AlertDialog.Builder builder = null;
                builder = new AlertDialog.Builder(this);
                builder.setTitle("使用條款")
                        .setMessage("『呷好米』APP 僅提供您快速查詢行政院農委會提供之市售包裝米之檢驗結果，不會蒐集您的任何個人資料，敬請安心使用。若使用上有任何意見，歡迎來信指教 goodrice.service@gmail.com")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
            case R.id.copyright:
                AlertDialog.Builder builder2 = null;
                builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("版權宣告")
                        .setMessage("『呷好米』APP 為 陳彥伃、蔡鴻淇、黃湧崑 於 2018年健行科技大學推廣教育中心-Android程式設計班，共同創作完成之成果。本APP僅為學習成果展現，無任何商業之運用。")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
            case R.id.disclaimer:
                AlertDialog.Builder builder3 = null;
                builder3 = new AlertDialog.Builder(this);
                builder3.setTitle("免責聲明")
                        .setMessage("『呷好米』APP 所提供之資料皆自『行政院農業委員會資料開放平台』取得，市售包裝米之檢驗結果與品質，本APP將不負任何相關之責任。若對資料內容有任何疑義，請以農委會資料開放平台網站為準。")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}