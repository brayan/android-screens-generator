<?xml version="1.0" encoding="utf-8"?>
<${tipoComponente} 
	<#list listaAtributos as atributo>
    ${atributo.nome}="${atributo.valor}"<#if atributo?is_last>></#if>
    </#list>

    <#list listaFilhos as filho>
    ${filho.tagXml}
    </#list>
</${tipoComponente}>