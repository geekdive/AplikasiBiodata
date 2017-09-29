package id.co.decoded.moeslim.meet060917;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class mainActivity extends AppCompatActivity {

    //Step i - Membuat Deklarasi Variable untuk tiap Widget
    Button btKirimInt;
    Button btKirimBund;
    Button btView;

    RadioButton rbtRPL;
    RadioButton rbtTKJ;
    RadioButton rbtAP;

    EditText edNama;
    EditText edAlamat;
    EditText edNoTelp;
    EditText edEmail;
    EditText edTglLhr;

    //Step ii - Membuat Variable String untuk variable pilih jurusan
    String choseProdi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step iii - Mengambil Aksi user dari Widget ke variable
        edNama = (EditText)findViewById(R.id.txtNama);
        edAlamat = (EditText)findViewById(R.id.txtAlamat);
        edNoTelp = (EditText)findViewById(R.id.txtTelp);
        edEmail = (EditText)findViewById(R.id.txtEmail);
        edTglLhr = (EditText)findViewById(R.id.txtTanggalLahir);

        btKirimInt = (Button)findViewById(R.id.btnKirimIntent);
        btKirimBund = (Button)findViewById(R.id.btnKirimBundle);
        btView = (Button)findViewById(R.id.btnTampilkan);

        rbtRPL = (RadioButton)findViewById(R.id.idRPL);
        rbtTKJ = (RadioButton)findViewById(R.id.idTKJ);
        rbtAP = (RadioButton)findViewById(R.id.idAP);

        btKirimBund.setEnabled(false);
        btKirimInt.setEnabled(false);

        //Step iv - Membuat button Aksi Klik
        btView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validasiInputan();
            }
        });
    }

    //Step v - Membuat Method aksi Pilih Jurusan Radio Button
    public void pilihJurusan(){
        if(rbtRPL.isChecked()){
            choseProdi = "Rekayasa Perangkat Lunak";
        }else if(rbtTKJ.isChecked()){
            choseProdi = "Teknik Komputer & Jaringan";
        }else if(rbtAP.isChecked()){
            choseProdi = "Administrasi Perkantoran";
        }else{
                //Step vi - Membuat Alert Dialog untuk Apabila User tidak memilih sama sekali
                //RadioButton Pilih Jurusan
                AlertDialog.Builder alertMessage = new AlertDialog.Builder(this);

                //Membuat Pesan Alert
                alertMessage
                        .setTitle("Peringatan!!")
                        .setMessage("Harap pilih terlebih dahulu, jurusannya!!")

                        //Untuk membuat aksi button Yes
                        //Tambahan : untuk membuat aksi ketik: new OnClickListener (*pilih kemudian Enter
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                //Menampilkan Alert Dialog
                AlertDialog alertDialog = alertMessage.create();
                alertDialog.show();
            }
        }


    //Step vii - Membuat validasi inputan user
    public void validasiInputan(){
        String emailValidator = edEmail.getText().toString();
        //Keterangan:
        //IF pertama: Maksudnya adalah
        //If kedua: Maksudnya adalah
        //Abiyyu
        if (edNama.getText().toString().isEmpty()){
            edNama.setError("Maaf, Nama Lengkap harus Diisi!!");
            edNama.requestFocus();
        }else if (!edNama.getText().toString().isEmpty() && edAlamat.getText().toString().isEmpty()){
            edAlamat.setError("Maaf, Alamat masih kosong!!");
            edAlamat.requestFocus();
        }else if (!edAlamat.getText().toString().isEmpty() && edNoTelp.getText().toString().isEmpty()){
            edNoTelp.setError("Maaf, No. Telp masih kosong!!");
            edNoTelp.requestFocus();
        }else if (!edNoTelp.getText().toString().isEmpty() && edEmail.getText().toString().isEmpty()){
            edEmail.setError("Maaf, Email masih kosong!!");
            edEmail.requestFocus();
        }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidator).matches()){
            edEmail.setError("Maaf, Email tidak Valid!!!!");
            edEmail.requestFocus();
            edEmail.setText("");
            edEmail.setHint("sample: sample@company.com");
        }else if (android.util.Patterns.EMAIL_ADDRESS.matcher(emailValidator).matches() && edTglLhr.getText().toString().isEmpty()){
            edTglLhr.setError("Maaf, Tanggal Lahir masih kosong!!");
            edTglLhr.requestFocus();
        }else{

            //Step viii - Ketika semua edittext sudah terisi maka akan menjalankan statement
            //selanjutnya yaitu dengan pertama : mengeksekusi metod pilihJurusan()
            pilihJurusan();
            //Jika jurusan sudah terpilih dan memiliki nilai selanjutnya akan ditampilkan kedalam
            //alert dialog
            if (choseProdi != null){
                AlertDialog.Builder alertTampilkanData = new AlertDialog.Builder(this);

                //Dimana kita set title dan message / pesan seperti berikut
                alertTampilkanData
                        .setTitle("Inputan Biodata Anda:")
                        .setMessage("Nama Lengkap : " + edNama.getText().toString()
                                + " \nAlamat : " + edAlamat.getText().toString()
                                + " \nEmail : " + edEmail.getText().toString()
                                + " \nNo. Telp : " + edNoTelp.getText().toString()
                                + " \nTanggal Lahir : " + edTglLhr.getText().toString()
                                + " \nJurusan : " + choseProdi)

                        //Step 9 - Membuat Button untuk melakukan pengiriman data jika sudah fix isinya
                        .setPositiveButton("Kirim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //tambahkan statement kirim data
                                kirimData();
                            }
                        })

                        //Lanjutan step 9 jika masih ada yang ingin dirubah datanya maka fungsi tombol
                        //Edit ini untuk kembali ke form awal dan memfokuskan ke EditText Nama Legkap
                        .setNegativeButton("Edit Ulang", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                edNama.requestFocus();
                            }
                        });
                AlertDialog alertDialog = alertTampilkanData.create();
                alertDialog.show();
            }
        }
    }

    //Step 10 - Membuat Metod untuk Mengirim atau mengangkut data ke Activity ke 2
    public void kirimData(){
        //Untuk coppas di IDE Android Studio menekan tombol keyboard kombinasi CTRL + D
        final String nama = edNama.getText().toString();
        final String alamat = edAlamat.getText().toString();
        final String email = edEmail.getText().toString();
        final String tglLhir = edTglLhr.getText().toString();
        final String noTelp = edNoTelp.getText().toString();

        //Membuat AdpBundle untuk mengangkut data
        Bundle angkut = new Bundle();
        //Memasukan Metod pilihJurusan() guna mengambil nilai dari pilihan User untuk
        //Jurusan
        pilihJurusan();

        angkut.putString("NAMALENGKAP", nama);
        angkut.putString("ALAMAT", alamat);
        angkut.putString("EMAIL", email);
        angkut.putString("TGLLHR", tglLhir);
        angkut.putString("TELP", noTelp);
        angkut.putString("JURUSAN", choseProdi);

        //Membuat Adapter Intent untuk Memindahkan aktivity1 ke activity 2
        Intent adpPindah = new Intent(mainActivity.this, dataActivity.class);
        adpPindah.putExtras(angkut);
        startActivity(adpPindah);


    }
}