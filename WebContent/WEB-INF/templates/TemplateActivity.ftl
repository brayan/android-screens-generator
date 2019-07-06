<#if nomePacote??>
package ${nomePacote};

</#if>
<#list listaComponentes as componente>
import ${componente.caminhoImport};
</#list>

public class ${nomeClasse} extends AppCompatActivity {

    <#list listaComponentes as componente>
    private ${componente.tipo} ${componente.nome};
    </#list>
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.${nomeArquivoXml});

        inicializarViews();
    }
    
    private void inicializarViews() {
         <#list listaComponentes as componente>
         this.${componente.nome} = (${componente.tipo}) findViewById(R.id.${componente.nome});
         </#list>
    }
    
}