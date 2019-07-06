var idComponentes = 0;

function definirComponentesDroppableSortable() {
	$("#componentesAdicionados").droppable({
		activeClass: "ui-state-default",
		hoverClass: "ui-state-hover",
		accept: ":not(.ui-sortable-helper)",
		drop: function( event, ui ) {
			
			if (possuiComponenteListaVazia()) {
				removerViewListaVazia();
			}
		        	
			var clone = $(ui.draggable).clone();
		            
			idComponentes = idComponentes + 1;
		            
			clone.addClass("componenteAdicionado").removeClass("componente");
			clone.children(".conteudoComponente").children("input").each(function () {
				var nome = $(this).attr("name");
				$(this).attr("name", nome + idComponentes);
			});
		            
			clone.children(".nomeComponente, .imagemComponente").click(function(){
				var conteudo = $(this).parent().children(".conteudoComponente");
				if (isConteudoVisivel(conteudo)) {
					conteudo.children(".trash").unbind( "click" );
					conteudo.hide();
					$(this).parent().css("height","80px");
					$(this).parent().css("width","200px");
				} else {
		            		
					conteudo.show();
					$(this).parent().css("height","400px");
					$(this).parent().css("width","400px");
					conteudo.children(".trash").click(function(){
						$(this).parent().parent().remove();
							if (!possuiComponentesAdicionados()) {
								adicionarComponentesListaVazia();
							}
						});
				}
			});
		            
			clone.appendTo(this);
		            
		}
		}).sortable({
			cursor: "move"
		});
	 }

	function adicionarComponentesListaVazia() {
		$('#componentesAdicionados').append('<div id="divEmptyView"><img src="img/drag.png" height="64" width="64"/><p id="pEmptyView">Arraste os componentes aqui</p></div>');
	}

	function possuiComponentesAdicionados() {
		return $('#componentesAdicionados').children(".componenteAdicionado").length != 0;
	}

	function possuiComponenteListaVazia() {
		return ($('#componentesAdicionados').children("#divEmptyView").length > 0);
	}
	
	function removerViewListaVazia() {
		$('#componentesAdicionados').children("#divEmptyView").remove();
	}
	 
	 function isConteudoVisivel(conteudo) {
		 return conteudo.is(":visible");
	 }
	 
	 function definirComponentesDraggable() {
		 $(".componente").draggable({
		        appendTo: "body",
		        helper: "clone",
		        revert: "invalid",
		        cursor: "move"
		    });
	 }
	 
	 function esconderConteudoComponentes() {
		 $(".conteudoComponente").hide();
	 }
	 
	 function definirListenerBotaoGerar() {
		 $("#botaoGerar").click(function() {
			 $( "#formComponentes" ).submit();
		 });
	 }
	 
	 function definirAcaoSubmitFormComponentes() {
		 $( "#formComponentes" ).submit(function(event) {
			 return isFormComponentesValido(); 
		 });
	 }
	 
	 function definirComponenteTooltip() {
		 $('[data-toggle="tooltip"]').tooltip();
	 }