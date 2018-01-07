package com.fichapp.activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fichapp.model.CNESModel;
import com.fichapp.R;
import com.fichapp.business.CNESBS;
import com.fichapp.util.Utilitario;

public class CNESActivity extends AppCompatActivity {

    private CNESModel cnesModel;
    private CNESBS cnesbs;
    private EditText cnesET;
    private EditText nomeET;
    private CheckBox mFlagAtivo;
    private Button gravarBT;
    private Integer qtdNome = 1;
    private Integer qtdCnes = 7;


    public CNESActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnes);

        getSupportActionBar().setTitle("Cadastro de Hospital");

        gravarBT = (Button) findViewById(R.id.bt_gravar);
        cnesET = (EditText) findViewById(R.id.codigo);
        nomeET = (EditText) findViewById(R.id.et_nome);
        mFlagAtivo = (CheckBox) findViewById(R.id.flag_ativo);

        cnesbs = new CNESBS(getApplicationContext());

        this.instanciarCNESModel();

        gravarBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gravar();
            }
        });

        leitorCampos();

    }

    private void instanciarCNESModel() {

        this.cnesModel = (CNESModel) getIntent().getSerializableExtra("cnes");

        if (this.cnesModel == null) {
            this.cnesModel = new CNESModel();
            mFlagAtivo.setChecked(Boolean.TRUE);
        } else {
            cnesET.setText(this.cnesModel.getCodigo());
            nomeET.setText(this.cnesModel.getNome());
            mFlagAtivo.setChecked(this.cnesModel.getFlagAtivo());
        }

    }

    private boolean validaCampos() {

        boolean valido = true;

        String aviso = "";

        if (Utilitario.isEmpty(nomeET.getText().toString())) {
            aviso = Utilitario.addAviso("O nomeET do hospital está vazio", aviso);
            valido = false;
        }

        if (Utilitario.isEmpty(cnesET.getText().toString())) {
            aviso = Utilitario.addAviso("O código CNES está vazio", aviso);
            valido = false;
        }

        if (!valido) {
            Snackbar.make(getCurrentFocus(), aviso, Snackbar.LENGTH_LONG).show();
        }

        return valido;
    }

    private void gravar() {

        if (!validaCampos()) {
            return;
        }

        this.cnesModel.setCodigo(cnesET.getText().toString());
        this.cnesModel.setNome(nomeET.getText().toString());
        this.cnesModel.setFlagAtivo(mFlagAtivo.isChecked());

        cnesbs.gravar(this.cnesModel);

        Utilitario.avisoSucesso(getApplicationContext());

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("fragment", "CNESFragment");
        startActivity(intent);

        finish();

    }


    private void leitorCampos() {

        qtdNome = 0;
        qtdCnes = 0;

        gravarBT.setEnabled(false);

        nomeET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                qtdNome = s.length();
                validadorBotao();
            }
        });

        cnesET.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                qtdCnes = s.length();
                validadorBotao();
            }
        });

    }


    private void validadorBotao() {

        gravarBT.setEnabled(false);

        if (qtdNome == 0 || qtdCnes != 6) {
            gravarBT.setEnabled(false);
        } else {
            gravarBT.setEnabled(true);
        }

    }

}
