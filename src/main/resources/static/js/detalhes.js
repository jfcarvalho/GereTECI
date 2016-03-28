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

$('#detalhesManutencao').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var idServico = button.data('id_servico');
			var protocolo = button.data('protocolo');
			var data_abertura = button.data('data_abertura');
			var data_encerramento = button.data('data_encerramento');
			var solicitante = button.data('solicitante');
			var atendente = button.data('atendente');
			var recolhimento = button.data('recolhimento');
			var trocarecursos = button.data('trocarecursos');
			var recurso_recolhido = button.data('recurso_recolhido');
			var recurso_substituido = button.data('recurso_substituto');
			var descricao_problema = button.data('descricao_problema');
			var descricao_pronta = button.data('descricao_pronta');
			var solucao_adotada = button.data('solucao_adotada');
			var status = button.data('status');
			
			var modal = $(this);
			
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + idServico);
				
				
			modal.find('.modal-body span').html('<p><b>Protocolo:</b><i> '+ protocolo +'</p></i> <b><p>Data de abertura:</b><i> '+ data_abertura + '</i></p><p><b>Data de encerraemnto:</b><i> '+ data_encerramento + '</i></p><b><p>Usuário solicitante:</b><i> '+ solicitante +'</i></p><p><b>Usuário Atendente: </b><i>'+ atendente + '</i></p><p><b>A máquina foi recolhida:: </b><i>'+ recolhimento + '</i></p><p><b>Houve troca de recursos?: </b><i>'+trocarecursos+ '</i></p><p><b>Recurso recolhido: </b><i>'+recurso_recolhido+ '</i></p><p><b>Recurso substituido: </b><i>'+ recurso_substituido + '</i></p><p><b>Descrição do problema: </b><i>'+ descricao_problema +'</i></p><p><b>Descricao pronta: </b><i>'+ descricao_pronta + '</i></p><b>Solução adotada: </b><i>'+ solucao_adotada + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
			
		});

$('#detalhesInternet').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var idServico = button.data('id_servico');
			var protocolo = button.data('protocolo');
			var data_abertura = button.data('data_abertura');
			var data_encerramento = button.data('data_encerramento');
			var solicitante = button.data('solicitante');
			var atendente = button.data('atendente');
			var proxy = button.data('proxy');
			var trocaip = button.data('trocaip');
			var ipantigo = button.data('ipantigo');
			var ipnovo = button.data('ipnovo');
			var descricao_problema = button.data('descricao_problema');
			var descricao_pronta = button.data('descricao_pronta');
			var solucao_adotada = button.data('solucao_adotada');
			var status = button.data('status');
			
			var modal = $(this);
			
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + idServico);
				
				
			modal.find('.modal-body span').html('<p><b>Protocolo:</b><i> '+ protocolo +'</p></i> <b><p>Data de abertura:</b><i> '+ data_abertura + '</i></p><p><b>Data de encerraemnto:</b><i> '+ data_encerramento + '</i></p><b><p>Usuário solicitante:</b><i> '+ solicitante +'</i></p><p><b>Usuário Atendente: </b><i>'+ atendente + '</i></p><p><b>Houve reinicialização do proxy:: </b><i>'+ proxy + '</i></p><p><b>Houve troca ip?: </b><i>'+trocaip+ '</i></p><p><b>Ip Antigo: </b><i>'+ ipantigo + '</i></p><p><b>IP Novo: </b><i>'+ ipnovo + '</i></p><p><b>Descrição do problema: </b><i>'+ descricao_problema +'</i></p><p><b>Descricao pronta: </b><i>'+ descricao_pronta + '</i></p><b>Solução adotada: </b><i>'+ solucao_adotada + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
			
		});
$('#detalhesRede').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var idServico = button.data('id_servico');
			var protocolo = button.data('protocolo');
			var data_abertura = button.data('data_abertura');
			var data_encerramento = button.data('data_encerramento');
			var solicitante = button.data('solicitante');
			var atendente = button.data('atendente');
			var criacao_usuario = button.data('criacao_usuario');
			var alteracao_usuario= button.data('alteracao_usuario');
			var exclusao_usuario = button.data('exclusao_usuario');
			var senha_expirada = button.data('senha_expirada');
			var descricao_problema = button.data('descricao_problema');
			var descricao_pronta = button.data('descricao_pronta');
			var solucao_adotada = button.data('solucao_adotada');
			var status = button.data('status');
			
			var modal = $(this);
			
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + idServico);
				
				
			modal.find('.modal-body span').html('<p><b>Protocolo:</b><i> '+ protocolo +'</p></i> <b><p>Data de abertura:</b><i> '+ data_abertura + '</i></p><p><b>Data de encerraemnto:</b><i> '+ data_encerramento + '</i></p><b><p>Usuário solicitante:</b><i> '+ solicitante +'</i></p><p><b>Usuário Atendente: </b><i>'+ atendente + '</i></p><p><b>Houve criação de conta de usuário?: </b><i>'+ criacao_usuario + '</i></p><p><b>Houve alteração de conta de usuário?: </b><i>'+alteracao_usuario+ '</i></p><p><b>Houve exclusao de conta de usuário?: </b><i>'+ exclusao_usuario + '</i></p><p><b>Houve senha expirada?: </b><i>'+ senha_expirada + '</i></p><p><b>Descrição do problema: </b><i>'+ descricao_problema +'</i></p><p><b>Descricao pronta: </b><i>'+ descricao_pronta + '</i></p><b>Solução adotada: </b><i>'+ solucao_adotada + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
			
		});
$('#detalhesTelefone').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var idServico = button.data('id_servico');
			var protocolo = button.data('protocolo');
			var data_abertura = button.data('data_abertura');
			var data_encerramento = button.data('data_encerramento');
			var solicitante = button.data('solicitante');
			var atendente = button.data('atendente');
			var nome_tecnico = button.data('nome_tecnico');
			var data_visita= button.data('data_visita');
			var protocolo_servico = button.data('protocolo_servico');
			var ramais_trocados = button.data('ramais_trocados');
			var descricao_problema = button.data('descricao_problema');
			var descricao_pronta = button.data('descricao_pronta');
			var solucao_adotada = button.data('solucao_adotada');
			var status = button.data('status');
			var visita_oi = button.data('visita_oi');
			var troca_ramal = button.data('troca_ramal');
			
			var modal = $(this);
			
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + idServico);
				
				
			modal.find('.modal-body span').html('<p><b>Protocolo:</b><i> '+ protocolo +'</p></i> <b><p>Data de abertura:</b><i> '+ data_abertura + '</i></p><p><b>Data de encerraemnto:</b><i> '+ data_encerramento + '</i></p><b><p>Usuário solicitante:</b><i> '+ solicitante +'</i></p><p><b>Usuário Atendente: </b><i>'+ atendente + '</i></p><p><b>Houve visita do tecnico da Oi?: </b><i>'+ visita_oi + '</i></p><p><b>Nome do técnico: </b><i>'+nome_tecnico+ '</i></p><p><b>Data da visita?: </b><i>'+ data_visita + '</i></p><p><b>Protocolo de servico(OI): </b><i>'+ protocolo_servico + '</i></p><p><b>Houve troca de ramais?: </b><i>'+ troca_ramal +'</i></p><p><b>Descricao pronta: </b><i>'+ descricao_pronta + '</i></p><b>Solução adotada: </b><i>'+ solucao_adotada + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
			
		});

$('#detalhesEmail').on('show.bs.modal', function(event)
		{
	var button = $(event.relatedTarget);
	var idServico = button.data('id_servico');
	var protocolo = button.data('protocolo');
	var data_abertura = button.data('data_abertura');
	var data_encerramento = button.data('data_encerramento');
	var solicitante = button.data('solicitante');
	var atendente = button.data('atendente');
	var criacao_usuario = button.data('criacao_usuario');
	var alteracao_usuario= button.data('alteracao_usuario');
	var exclusao_usuario = button.data('exclusao_usuario');
	var senha_expirada = button.data('senha_expirada');
	var descricao_problema = button.data('descricao_problema');
	var descricao_pronta = button.data('descricao_pronta');
	var solucao_adotada = button.data('solucao_adotada');
	var status = button.data('status');
	var mudanca_plano = button.data('mudanca_plano');
	var conta = button.data('conta');
	var modal = $(this);
	
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/'))
		{
			action += '/';
		}
	
		form.attr('action', action + idServico);
		
		
	modal.find('.modal-body span').html('<p><b>Protocolo:</b><i> '+ protocolo +'</p></i> <b><p>Data de abertura:</b><i> '+ data_abertura + '</i></p><p><b>Data de encerraemnto:</b><i> '+ data_encerramento + '</i></p><b><p>Usuário solicitante:</b><i> '+ solicitante +'</i></p><p><b>Usuário Atendente: </b><i>'+ atendente + '</i></p><p><b>Houve criação de conta de usuário?: </b><i>'+ criacao_usuario + '</i></p><p><b>Houve alteração de conta de usuário?: </b><i>'+alteracao_usuario+ '</i></p><p><b>Houve alteração plano de conta?: </b><i>'+mudanca_plano+ '</i></p><p><b>Houve exclusao de conta de usuário?: </b><i>'+ exclusao_usuario + '</i></p><p><b>Houve senha expirada?: </b><i>'+ senha_expirada + '</i></p><p><b>Descrição do problema: </b><i>'+ descricao_problema +'</i></p><p><b>Descricao pronta: </b><i>'+ descricao_pronta + '</i></p><b>Solução adotada: </b><i>'+ solucao_adotada + '</i></p><p><b>Status: </b><i>'+status+'</p></i>');
		
		});

