package com.front.zkoos.module.form;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Div;

public class Controlador extends Borderlayout implements AfterCompose{

    public void afterCompose() {
        System.out.print("capturo el evento1");
    }
    public void probar(){
        System.out.print("capturo el evento2");
    }
    public void RegistrarCliente(){
	this.cargarZul("RegistrarCliente.zul");
    }
    public void ModificarCliente(){
        this.cargarZul("fsz_negociante.zul");
    }
    public void GestionarRaza(){
        this.cargarZul("fsz_raza.zul");
    }
    public void GestionarTipoMedicina(){
        this.cargarZul("fsz_tipo_medicina.zul");
    }
    public void GestionarMedicamento(){
        this.cargarZul("fsz_medicamento.zul");
    }
    public void GestionarAnimal(){
        this.cargarZul("fsz_animal.zul");
    }
    public void cargarZul(String zul){
//		Component c=this.getParent();
//		this.detach();
//		Executions.createComponents(zul,c, null);
        Div div=(Div)this.getFellow("contenedor");
        div.getChildren().clear();
        Executions.createComponents(zul, div, null);
    }
    
}
