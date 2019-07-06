<${tipoComponente} 
    <#list listaAtributos as atributo>
        ${atributo.nome}="${atributo.valor}"<#if atributo?is_last>/></#if>
    </#list>