/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ep2ocd;

// TUTORIAL - http://docs.oracle.com/javafx/2/get_started/jfxpub-get_started.htm

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
/**
 *
 * @author a5633965
 */
public class SampleController implements Initializable {

    @FXML
    private TextArea entrada;
    @FXML
    private TextArea saida;
    @FXML
    private TextField pc;
    @FXML
    private TextField mar;
    @FXML
    private TextField mbr;
    @FXML
    private TextField ax;
    @FXML
    private TextField bx;
    @FXML
    private TextField cx;
    @FXML
    private TextField dx;
    @FXML
    private TextField ir;
    @FXML
    private TextField x;
    @FXML
    private TextField ula;
    @FXML
    private TextField ac;
    @FXML
    private TextField zero;
    @FXML
    private TextField sinal;
    
    private int ilinha = 1;
    private int inicio = 22;
    private int inicioassembly = 0;
    private int qntlinhacodigo;
    private int linhaexecutada = 0;

    private Memoria mem = new Memoria();

    @FXML
    private void executar(ActionEvent event) {
        try {
            //executa a proxima linha do microprograma
            if (linhaexecutada < qntlinhacodigo) {//ainda tem linhas a serem executadas

                String microprograma = saida.getText();
                String micro[] = microprograma.split("\n");

                //System.out.println(micro[ilinha]);
                Portas pt = Codigo.parseCodigo(micro[ilinha], zero.getText(), sinal.getText(), ir.getText());
                ilinha++;
                String aux = null;
                String ops[];
                switch (pt.psaida) {
                    case ("pc"):
                        aux = pc.getText();
                        int k = Integer.parseInt(aux);
                        aux = Integer.toString(k, 16);
                        break;
                    case ("mbr"):
                        aux = mbr.getText();
                        break;
                    case ("ax"):
                        aux = ax.getText();
                        break;
                    case ("bx"):
                        aux = bx.getText();
                        break;
                    case ("cx"):
                        aux = cx.getText();
                        break;
                    case ("dx"):
                        aux = dx.getText();
                        break;
                    case ("ac"):
                        aux = ac.getText();
                        break;
                    case ("p1"):
                        ops = ir.getText().split(" ");
                        aux = ops[1];
                        break;
                    case ("p2"):
                        ops = ir.getText().split(" ");
                        aux = ops[2];
                        break;
                    default:
                    //System.out.println("veio null");
                }
                switch (pt.pentrada) {
                    case ("pc"):
                        int k = Integer.parseInt(aux, 16);
                        pc.setText(Integer.toString(k));
                        break;
                    case ("mar"):
                        mar.setText(aux);
                        break;
                    case ("mbr"):
                        mbr.setText(aux);
                        break;
                    case ("ax"):
                        ax.setText(aux);
                        break;
                    case ("bx"):
                        bx.setText(aux);
                        break;
                    case ("cx"):
                        cx.setText(aux);
                        break;
                    case ("dx"):
                        dx.setText(aux);
                        break;
                    case ("x"):
                        x.setText(aux);
                        break;
                    case ("ula"):
                        ula.setText(aux);
                        break;
                    case ("ir"):
                        ir.setText(aux);
                        String opcde[] = aux.split(" ");
                        if ("101110001".equals(opcde[0]) || "110011000".equals(opcde[0])) {
                            Codigo.usoup1 = true;
                        }
                        break;
                    default:
                    //System.out.println("veio null");
                }
                
                String auxmem = null;
                switch (pt.psaidaext) {
                    case ("mar"):
                        auxmem = mar.getText();
                        break;
                    case ("mbr"):
                        auxmem = mbr.getText();
                        break;
                    case ("mem"):
                        auxmem = mem.readBuffer();
                        break;
                    default:
                    //System.out.println("veio null");
                }
                switch (pt.pentradaext) {
                    case ("mbr"):
                        mbr.setText(auxmem);
                        break;
                    case ("mem"):
                        if (pt.av) {
                            if ("0".equals(pt.rw)) { //read
                                mem.read(Integer.parseInt(auxmem, 16));
                            } else {//write
                                mem.write(Integer.parseInt(auxmem, 16));
                            }
                        } else {
                            mem.writeBuffer(auxmem);
                        }
                        break;
                    default:
                    //System.out.println("veio null");
                }
                
                if (!"nada".equals(pt.ula)) {
                    boolean mod = false;
                    int a;
                    switch (pt.ula) {
                        case ("+"):
                            a = Integer.parseInt(x.getText(), 16) + Integer.parseInt(ula.getText(), 16);
                            break;
                        case ("-"):
                            a = Integer.parseInt(x.getText(), 16) - Integer.parseInt(ula.getText(), 16);
                            break;
                        case ("/"):
                            a = Integer.parseInt(x.getText(), 16) / Integer.parseInt(ula.getText(), 16);
                            break;
                        case ("*"):
                            a = Integer.parseInt(x.getText(), 16) * Integer.parseInt(ula.getText(), 16);
                            break;
                        case ("inc"):
                            a = Integer.parseInt(ula.getText(), 16) + 1;
                            break;
                        case ("dec"):
                            a = Integer.parseInt(ula.getText(), 16) - 1;
                            break;
                        case ("mod"):
                            a = Integer.parseInt(x.getText(), 16) % Integer.parseInt(ula.getText(), 16);
                            mod = true;
                        default:
                            a = 0;
                    }
                    if (mod || "-1".equals(pt.end)) {
                        //a operacao mod não seta as flags
                    } else {
                        if (a == 0) {
                            zero.setText("1");
                        } else {
                            zero.setText("0");
                        }
                        if (a < 0) {
                            sinal.setText("1");
                        } else {
                            sinal.setText("0");
                        }
                    }
                    ac.setText(Integer.toString(a, 16));
                }
                
                if (pt.pulo) {
                    if ("<OPCODE>".equals(pt.end) || "000000010".equals(pt.end) || "000000111".equals(pt.end)) {
                        //pulo pra outra parte do microprograma
                        String ircode = ir.getText();
                        String[] opcode = ircode.split(" ");
                        String endereco;
                        if ("<OPCODE>".equals(pt.end)) {
                            endereco = opcode[0];
                        } else {
                            endereco = pt.end;
                        }
                        
                        int end = Integer.parseInt(endereco, 2);
                        if (end == 2) {//voltando pro ciclo de busca, vai executar proxima linha
                            linhaexecutada = Integer.parseInt(pc.getText());
                            Codigo.usoup1 = false;
                            //fazer highlight da entrada
                            String input = entrada.getText();
                            String comandos[] = input.split("\n");
                            for (int i = 0; i < linhaexecutada; i++) {
                                inicioassembly = inicioassembly + comandos[i].length() + 1;
                            }
                            if (linhaexecutada < qntlinhacodigo) {
                                entrada.selectRange(inicioassembly, inicioassembly + comandos[linhaexecutada].length());
                                inicioassembly = 0;
                            }
                        }
                        saida.setText(Codigo.uc.get(end - 1) + "\n");
                        String code = Codigo.uc.get(end) + "\n";
                        while (code.startsWith("0") || code.startsWith("1")) {
                            saida.appendText(code);
                            end++;
                            code = Codigo.uc.get(end) + "\n";
                        }
                        
                        ilinha = 1;
                        inicio = 22;
                    } else { //pulo interno dos comandos jump
                        int end = Integer.parseInt(pt.end, 2);
                        saida.setText(Codigo.uc.get(end - 3) + "\n");
                        saida.appendText(Codigo.uc.get(end - 2) + "\n");
                        saida.appendText(Codigo.uc.get(end - 1) + "\n");
                        saida.appendText(Codigo.uc.get(end) + "\n");
                        
                        ilinha = 3;
                        inicio = 22 + 49 + 49;
                    }
                }

                //highlight            
                saida.selectRange(inicio, inicio + 48);
                inicio = inicio + 49;
            } else {
                saida.setText("Fim do Codigo");
            }
        } catch (Exception e) {
            entrada.setText("ERRO");
        }
    }
    
    @FXML
    private void iniciar(ActionEvent event) {
        try {
            //carrega o codigo assembly na memoria
            Codigo.firmware();
            ax.clear();
            bx.clear();
            cx.clear();
            dx.clear();
            mar.clear();
            mbr.clear();
            ir.clear();
            x.clear();
            ula.clear();
            ac.clear();
            zero.clear();
            sinal.clear();
            pc.setText("0");
            
            String input = entrada.getText();
            String comandos[] = input.split("\n");
            for (int i = 0; i < comandos.length; i++) {
                String aux = Codigo.translate(comandos[i]);
                mem.add(i, aux);
            }
            qntlinhacodigo = comandos.length;
            linhaexecutada = 0;
            saida.clear();
            saida.appendText(Codigo.uc.get(1) + "\n");
            saida.appendText(Codigo.uc.get(2) + "\n");
            saida.appendText(Codigo.uc.get(3) + "\n");
            saida.appendText(Codigo.uc.get(4) + "\n");
            saida.appendText(Codigo.uc.get(5) + "\n");

            //highlight        
            inicio = 22;
            saida.selectRange(inicio, inicio + 48);
            inicio = inicio + 49;
            
            inicioassembly = 0;
            entrada.selectRange(inicioassembly, inicioassembly + comandos[0].length());
        } catch (Exception e) {
            entrada.setText("ERRO");
        }
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    
    
        
    
}
