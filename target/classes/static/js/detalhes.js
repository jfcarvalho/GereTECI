$('#detalhesComputador').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoComputador = button.data('id_computador');
			var ipComputador = button.data('ip');
			var dataCompra = button.data('data_compra');
			var dataFormatacao = button.data('data_formatacao');
			var patrimonio = button.data('patrimonio');
			var sistema = button.data('sistema');
			var arquitetura = button.data('arquitetura');
			var versao_java = button.data('versao_java');
			var dataBackup = button.data('data_backup');
			var dns_preferencial = button.data('dns_preferencial');
			var dns_alternativo = button.data('dns_alternativo');
			var mascara = button.data('mascara');
			var gateway = button.data('gateway');
			var id_impressao = button.data('id_impressao');
			var status = button.data('status');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoComputador);
				
				modal.find('.modal-body span').html('<p><b>Número de patrimonio:</b><i> '+ patrimonio +'</p></i> <b><p>Sistema Operacional:</b><i> '+ sistema + '</i></p><p><b>IP:</b><i> '+ ipComputador + '</i></p><b><p>Máscara:</b><i> '+ mascara +'</i></p><p><b>DNS Preferencial: </b><i>'+ dns_preferencial + '</i></p><p><b>Dns Alternativo: </b><i>'+ dns_alternativo + '</i></p><p><b>Gateway: </b><i>'+gateway+ '</i></p><p><b>Versão do Java: </b><i>'+versao_java+ '</i></p><p><b>Data de compra: </b><i>'+ dataCompra + '</i></p><p><b>Data da última formatação: </b><i>'+ dataFormatacao+'</i></p><p><b>Data do último backup: </b><i>'+ dataBackup + '</i></p><b>ID de impressão: </b><i>'+ id_impressao + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
			
		});
