package com.alzira.database;

import com.alzira.model.CarrinhoModel;
import com.alzira.model.ProdutoCarrinhoModel;
import com.alzira.model.ProdutoModel;
import com.alzira.model.ProdutoViewModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {
  
    public List execSelect() throws JsonProcessingException, SQLException, InterruptedException {

        List<CarrinhoModel> lista = new ArrayList<>();

        List<Integer> listaID = new ArrayList<>();

        Database.Conectar();
        ResultSet resultado = Database.Ler("SELECT * FROM COMPRAS");
            
        Integer db_compraId = null;
        String db_nomeProduto = null;
        Integer db_quantidade = null;
        Double db_precoUnitario = null;
        Double db_valorTotal = null;

        while (resultado.next()) {
            List<ProdutoCarrinhoModel> listaProdutos = new ArrayList<>();

            db_compraId = resultado.getInt("compraid");
            
            if (listaID.contains(db_compraId)){
                continue;
            }

            listaID.add(db_compraId);

            ResultSet resultadoAux = Database.Ler("SELECT * FROM VW_VISUALIZACARRINHO WHERE COMPRAID = " + db_compraId);

            Double valorTotal = 0.0;

            while (resultadoAux.next()){
                db_nomeProduto = resultadoAux.getString("nomeproduto");
                db_quantidade = resultadoAux.getInt("quantidade");
                db_precoUnitario = resultadoAux.getDouble("precounitario");
                db_valorTotal = resultadoAux.getDouble("valortotal");

                valorTotal += db_valorTotal;

                ProdutoCarrinhoModel produto = new ProdutoCarrinhoModel(db_nomeProduto, db_quantidade, db_precoUnitario, db_valorTotal);

                listaProdutos.add(produto);
            }

            lista.add(new CarrinhoModel(db_compraId, listaProdutos, valorTotal));
        }
        
        Database.Desconectar();
        
        return lista;
    }  
}