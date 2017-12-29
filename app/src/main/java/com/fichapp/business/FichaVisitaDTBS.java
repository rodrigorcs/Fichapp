package com.fichapp.business;

import android.content.Context;

import com.fichapp.model.FichaVisitaDTModel;
import com.fichapp.dao.FichaVisitaDTDAO;
import com.fichapp.dao.SMPEPDbHelper;

import java.util.List;

/**
 * Created by Rodrigo Costa on 11/12/2017.
 */

public class FichaVisitaDTBS {

    FichaVisitaDTDAO fichaVisitaDTDAO;

    public FichaVisitaDTBS() {
    }

    public FichaVisitaDTBS(Context context) {
        SMPEPDbHelper smpepDbHelper = new SMPEPDbHelper(context);
        fichaVisitaDTDAO = new FichaVisitaDTDAO(smpepDbHelper);
    }

    public void gravar(FichaVisitaDTModel fichaModel) {

        if (fichaModel.getId() != null) {
            fichaVisitaDTDAO.alterar(fichaModel);
        } else {
            fichaVisitaDTDAO.inserir(fichaModel);
        }

    }

    public void excluir(FichaVisitaDTModel fichaModel) {
        fichaVisitaDTDAO.excluir(fichaModel);
        //profissionalDAO.pesquisar();
    }

    public FichaVisitaDTModel obter(FichaVisitaDTModel fichaVisitaDTModel) {

        return this.fichaVisitaDTDAO.obter(fichaVisitaDTModel);
    }

    public List<FichaVisitaDTModel> pesquisar() {
        return fichaVisitaDTDAO.pesquisar();
    }

    public List<FichaVisitaDTModel> pesquisarAtivo() {
        return fichaVisitaDTDAO.pesquisarAtivos();
    }



}
