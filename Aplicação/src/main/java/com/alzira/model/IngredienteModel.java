/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alzira.model;

/**
 *
 * @author Renanzed
 */
public class IngredienteModel {
    public Integer id;
    public String nomeIngrediente;
    public Double precoUnit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeIngrediente() {
        return nomeIngrediente;
    }

    public void setNomeIngrediente(String nomeIngrediente) {
        this.nomeIngrediente = nomeIngrediente;
    }

    public Double getPrecoUnit() {
        return precoUnit;
    }

    public void setPrecoUnit(Double precoUnit) {
        this.precoUnit = precoUnit;
    }

    public IngredienteModel(String nomeIngrediente, Double precoUnit) {
        this.nomeIngrediente = nomeIngrediente;
        this.precoUnit = precoUnit;
    }
    
    
}
