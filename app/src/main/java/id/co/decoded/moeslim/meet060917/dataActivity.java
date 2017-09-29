package id.co.decoded.moeslim.meet060917;

import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class dataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        TextView tvNama = (TextView)findViewById(R.id.namaLengkap);
        TextView tvEmail = (TextView)findViewById(R.id.alamatEmail);
        TextView tvAlamat = (TextView)findViewById(R.id.alamatLengkap);
        TextView tvNoTelp = (TextView)findViewById(R.id.noTelp);
        TextView tvTglLhr = (TextView)findViewById(R.id.tanggalLahir);
        TextView tvJurusan = (TextView)findViewById(R.id.jurusan);


        if (getIntent().getExtras() != null){
            Bundle tampungBundle = getIntent().getExtras();
            tvNama.setText(tampungBundle.getString("NAMALENGKAP"));
            tvEmail.setText(tampungBundle.getString("ALAMAT"));
            tvAlamat.setText(tampungBundle.getString("EMAIL"));
            tvNoTelp.setText(tampungBundle.getString("TGLLHR"));
            tvTglLhr.setText(tampungBundle.getString("TELP"));
            tvJurusan.setText(tampungBundle.getString("JURUSAN"));
        }else{
            Toast.makeText(dataActivity.this, "Data yang kirim tidak ada!!", Toast.LENGTH_SHORT).show();
        }
    }
}
