package com.sanchez.jesus.protest;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;


public class Anadir extends AppCompatActivity {

    private static final String TAG="activity_splash_screen";
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context myContext;
    private CoordinatorLayout coordinatorLayout;
    private EditText edtext1, edtext2, edtext3,  edtext4, edtext5;
    private Spinner spinner1;
    private Button bt;
    private Button button;
    private boolean editar = false;
    private int codigo;
    private ArrayList<String> categorias = new ArrayList<>();
    private ArrayAdapter<String> adapter;
    private Repositorio r = Repositorio.getInstance();
    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    final String pathFotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/demoAndroidImages/";
    private Uri uri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myContext=this;

        r.consultaCategorias(myContext);

        try {
            categorias = r.getCategorias();
        }catch (NullPointerException ex) {
            categorias = new ArrayList<>();
        }

        spinner1 = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias);
        spinner1.setAdapter(adapter);


        bt = (Button) findViewById(R.id.botonAceptar);
        edtext1 = (EditText) findViewById(R.id.PreguntaeditText);
        edtext2 = (EditText) findViewById(R.id.respuestaCorrecta);
        edtext3 = (EditText) findViewById(R.id.respuestaIncorrecta1);
        edtext4 = (EditText) findViewById(R.id.respuestaIncorrecta2);
        edtext5 = (EditText) findViewById(R.id.respuestaIncorrecta3);

        editar = getIntent().getExtras().getBoolean(Constantes.EDITAR);
        codigo = getIntent().getExtras().getInt(Constantes.CODPREGUNTA);
        edtext1.setText(getIntent().getExtras().getString(Constantes.ENUNCIADO));
        spinner1.setSelection(categorias.indexOf(getIntent().getExtras().getString(Constantes.CATEGORIA)));
        edtext2.setText(getIntent().getExtras().getString(Constantes.RESPCORRECTA));
        edtext3.setText(getIntent().getExtras().getString(Constantes.RESPINCORRECTA1));
        edtext4.setText(getIntent().getExtras().getString(Constantes.RESPINCORRECTA2));
        edtext5.setText(getIntent().getExtras().getString(Constantes.RESPINCORRECTA3));


       button = (Button) findViewById(R.id.botoncat);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);
                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);
                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);
                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.add),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        // Añade la categoria introducida al spinner
                                        categorias.add(dialogInput.getText().toString());
                                        // Eliminar posibles duplicados
                                        HashSet<String> quitarDuplicados = new HashSet<>(categorias);
                                        categorias =  new ArrayList<>(quitarDuplicados);
                                        // readapta el adaptador al arraylist. Necesario debido a errores de actualizar array
                                        adapter = new ArrayAdapter<String>(myContext, R.layout.support_simple_spinner_dropdown_item, categorias);
                                        spinner1.setAdapter(adapter);
                                        // Seleccionar la categoria introducida
                                        spinner1.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                    }
                                })
                        // Botón Cancelar
                        .setNegativeButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }
        });

        //En este boton al pulsar examina todos los campos para ver si estan todos rellenados
        //si no muestra un snackbar indicandoque rellenemos ese campo
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(bt.getWindowToken(), 0);
                if (edtext1.getText().toString().isEmpty()) {
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext2.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext3.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext4.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }else if(edtext5.getText().toString().isEmpty()){
                    Snackbar.make(v, "Rellenar todos los campos", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{

                //Cuando esten todos los campos rellenados nos mostrará un alert preguntando que si aceptamos los permisos

                    //AQUI ESTABA LOS PERMISOS
                   /* myContext = Anadir.this;
                    coordinatorLayout = findViewById(R.id.coordinatorLayout);
                    bt.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                            Log.d("AnadirActivity", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

                            if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                                // Permiso denegado
                                // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
                                // En las versiones anteriores no es posible hacerlo
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                    ActivityCompat.requestPermissions(Anadir.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                                    // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                                    // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
                                } else {
                                    Snackbar.make(coordinatorLayout, getResources().getString(R.string.write_permiso_denegado), Snackbar.LENGTH_LONG)
                                            .show();
                                }
                            } else {
                                // Permiso aceptado
                                Snackbar.make(coordinatorLayout, getResources().getString(R.string.write_permiso_aceptado), Snackbar.LENGTH_LONG)
                                        .show();
                            }

                        }
                    });*/

                    //Hay un if que indica que si el editar es false insertara los datos nuevos.
                    if (editar==false) {


                        Pregunta pregunta = new Pregunta(edtext1.getText().toString(), spinner1.getSelectedItem().toString(), edtext2.getText().toString(), edtext3.getText().toString(), edtext4.getText().toString(), edtext5.getText().toString());

                        Repositorio.insertar(myContext, pregunta);

                        finish();
                    }else{

                        //Mientras que si los datos ya estan introducidos se editara los datos
                        Pregunta pregunta = new Pregunta(edtext1.getText().toString(), spinner1.getSelectedItem().toString(), edtext2.getText().toString(), edtext3.getText().toString(), edtext4.getText().toString(), edtext5.getText().toString());

                        pregunta.setCodigo(codigo);

                        Repositorio.editarpregu(myContext, pregunta);

                        finish();
                    }
                }

            }
        });

        Button buttonCamera = findViewById(R.id.buttonCamera);

        if(!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            buttonCamera.setEnabled(false);
        } else {
            buttonCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
                        takePicture();
                    }
                }
            });
        }

        Button buttonGallery = (Button) findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPicture();
            }
        });
}

    private void takePicture() {
        try {
            // Se crea el directorio para las fotografías
            File dirFotos = new File(pathFotos);
            dirFotos.mkdirs();

            // Se crea el archivo para almacenar la fotografía
            File fileFoto = File.createTempFile(getFileCode(),".jpg", dirFotos);

            // Se crea el objeto Uri a partir del archivo
            // A partir de la API 24 se debe utilizar FileProvider para proteger
            // con permisos los archivos creados
            // Con estas funciones podemos evitarlo
            // https://stackoverflow.com/questions/42251634/android-os-fileuriexposedexception-file-jpg-exposed-beyond-app-through-clipdata
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            uri = Uri.fromFile(fileFoto);
            Log.d(TAG, uri.getPath().toString());

            // Se crea la comunicación con la cámara
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // Se le indica dónde almacenar la fotografía
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            // Se lanza la cámara y se espera su resultado
            startActivityForResult(cameraIntent, REQUEST_CAPTURE_IMAGE);

        } catch (IOException ex) {

            Log.d(TAG, "Error: " + ex);
            CoordinatorLayout coordinatorLayout = findViewById(R.id.coordinatorLayout);
            Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, getResources().getString(R.string.error_files), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    private void selectPicture(){
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if(resultCode == Activity.RESULT_OK){
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bmp);
                    }
                }
                break;
        }
    }

    private String getFileCode()
    {
        // Se crea un código a partir de la fecha y hora
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss", java.util.Locale.getDefault());
        String date = dateFormat.format(new Date());
        // Se devuelve el código
        return "pic_" + date;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    protected void onStart() {
        Mylog.d(TAG, "Iniciando OnStart");
        super.onStart();
        Mylog.d(TAG, "Finalizando OnStart");
    }

    @Override
    protected void onResume() {
        Mylog.d(TAG, "Iniciando OnResume");
        super.onResume();
        Mylog.d(TAG, "Finalizando OnResume");
    }

    @Override
    protected void onPause() {
        Mylog.d(TAG, "Iniciando OnPause");
        super.onPause();
        Mylog.d(TAG, "Finalizando OnPause");
    }

    @Override
    protected void onStop() {
        Mylog.d(TAG, "Iniciando OnStop");
        super.onStop();
        Mylog.d(TAG, "Finalizando OnStop");
    }

    @Override
    protected void onRestart() {
        Mylog.d(TAG, "Iniciando OnRestart");
        super.onRestart();
        Mylog.d(TAG, "Finalizando OnRestart");
    }

    @Override
    protected void onDestroy() {
        Mylog.d(TAG, "Iniciando OnDestroy");
        super.onDestroy();
        Mylog.d(TAG, "Finalizando OnDestroy");
    }
}
