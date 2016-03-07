$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event)
{
	var button = $(event.relatedTarget);
	var codigoUsuario = button.data('id_usuario');
	var nomeUsuario = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/'))
		{
			action += '/';
		}
	
		form.attr('action', action + codigoUsuario);
		modal.find('.modal-body span').html('Tem certeza que deseja excluir o usuario <strong>' + nomeUsuario + '</strong>?');
	
	
});

$('#confirmacaoExclusaoModalComputador').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoComputador = button.data('id_computador');
			var ipComputador = button.data('ip');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoComputador);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir o conmputador de IP: <strong>' + ipComputador + '</strong>?');
			
			
		});
$('#confirmacaoExclusaoModalSetor').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoSetor = button.data('id_setor');
			var nomeSetor = button.data('nome');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoSetor);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir o setor <strong>' + nomeSetor+ '</strong>?');
			
			
		});
$('#confirmacaoExclusaoModalRecurso').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoRecurso = button.data('id_recurso');
			var nomeRecurso= button.data('descricao');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoRecurso);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir o recurso <strong>' + nomeRecurso+ '</strong>?');
			
			
		});
$('#confirmacaoExclusaoModalImpressora').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoImpressora = button.data('id_impressora');
			var marcaImpressora= button.data('marca');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoImpressora);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir a impressora <strong>' + marcaImpressora+ '</strong>?');
			
			
		});
$('#confirmacaoExclusaoModalLicenca').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoLicenca = button.data('id_licenca');
			var nomeLicenca= button.data('nome');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoLicenca);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir a Licenca <strong>' + nomeLicenca + '</strong>?');
			
			
		});
$('#confirmacaoExclusaoModalLicencaOffice').on('show.bs.modal', function(event)
		{
			var button = $(event.relatedTarget);
			var codigoLicenca = button.data('id_licencaoffice');
			var nomeLicenca= button.data('nome');
			
			var modal = $(this);
			var form = modal.find('form');
			var action = form.data('url-base');
			if(!action.endsWith('/'))
				{
					action += '/';
				}
			
				form.attr('action', action + codigoLicenca);
				modal.find('.modal-body span').html('Tem certeza que deseja excluir a Licenca Office <strong>' + nomeLicenca + '</strong>?');
			
			
		});

$(function() {
	$('.js-atualizar-status-manutencao-computador').on('click', function(event) {
		event.preventDefault();
		var botaoManutencao = $(event.currentTarget);
		var urlManutencao = botaoManutencao.attr('href'); 
		console.log('urlManutencao', urlManutencao);
		
		var response = $.ajax({
			url: urlManutencao, 
			type: 'PUT'
				
		});
		
		response.done(function(e) {
			var codigoManutencao = botaoManutencao.data('id_computador');
			console.log(codigoManutencao);
			$('[data-role=' + codigoManutencao + ']').html('<span class="label label-warning">' + e + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-baixa-computador').on('click', function(event) {
		event.preventDefault();
		var botaoBaixa= $(event.currentTarget);
		var urlBaixa = botaoBaixa.attr('href'); 
		console.log('urlBaixa', urlBaixa);
		
		var response = $.ajax({
			url: urlBaixa, 
			type: 'PUT'
				
		});
		
		response.done(function(a) {
			var codigoBaixa= botaoBaixa.data('id_computador');
			console.log(codigoBaixa);
			$('[data-role=' + codigoBaixa+ ']').html('<span class="label label-danger">'+ a + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-conserto-computador').on('click', function(event) {
		event.preventDefault();
		var botaoFuncionando= $(event.currentTarget);
		var urlFuncionando= botaoFuncionando.attr('href'); 
		console.log('urlFuncionando', urlFuncionando);
		
		var response = $.ajax({
			url: urlFuncionando, 
			type: 'PUT'
				
		});
		
		response.done(function(s) {
			var codigoFuncionando= botaoFuncionando.data('id_computador');
			console.log(codigoFuncionando);
			$('[data-role=' + codigoFuncionando+ ']').html('<span class="label label-success">'+ s + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-manutencao-impressora').on('click', function(event) {
		event.preventDefault();
		var botaoManutencao = $(event.currentTarget);
		var urlManutencao = botaoManutencao.attr('href'); 
		console.log('urlManutencao', urlManutencao);
		
		var response = $.ajax({
			url: urlManutencao, 
			type: 'PUT'
				
		});
		
		response.done(function(e) {
			var codigoManutencao = botaoManutencao.data('id_impressora');
			console.log(codigoManutencao);
			$('[data-role=' + codigoManutencao + ']').html('<span class="label label-warning">' + e + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-baixa-impressora').on('click', function(event) {
		event.preventDefault();
		var botaoBaixa= $(event.currentTarget);
		var urlBaixa = botaoBaixa.attr('href'); 
		console.log('urlBaixa', urlBaixa);
		
		var response = $.ajax({
			url: urlBaixa, 
			type: 'PUT'
				
		});
		
		response.done(function(a) {
			var codigoBaixa= botaoBaixa.data('id_impressora');
			console.log(codigoBaixa);
			$('[data-role=' + codigoBaixa+ ']').html('<span class="label label-danger">'+ a + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-conserto-impressora').on('click', function(event) {
		event.preventDefault();
		var botaoFuncionando= $(event.currentTarget);
		var urlFuncionando= botaoFuncionando.attr('href'); 
		console.log('urlFuncionando', urlFuncionando);
		
		var response = $.ajax({
			url: urlFuncionando, 
			type: 'PUT'
				
		});
		
		response.done(function(s) {
			var codigoFuncionando= botaoFuncionando.data('id_impressora');
			console.log(codigoFuncionando);
			$('[data-role=' + codigoFuncionando+ ']').html('<span class="label label-success">'+ s + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-manutencao-recurso').on('click', function(event) {
		event.preventDefault();
		var botaoManutencao = $(event.currentTarget);
		var urlManutencao = botaoManutencao.attr('href'); 
		console.log('urlManutencao', urlManutencao);
		
		var response = $.ajax({
			url: urlManutencao, 
			type: 'PUT'
				
		});
		
		response.done(function(e) {
			var codigoManutencao = botaoManutencao.data('id_recurso');
			console.log(codigoManutencao);
			$('[data-role=' + codigoManutencao + ']').html('<span class="label label-warning">' + e + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-baixa-recurso').on('click', function(event) {
		event.preventDefault();
		var botaoBaixa= $(event.currentTarget);
		var urlBaixa = botaoBaixa.attr('href'); 
		console.log('urlBaixa', urlBaixa);
		
		var response = $.ajax({
			url: urlBaixa, 
			type: 'PUT'
				
		});
		
		response.done(function(a) {
			var codigoBaixa= botaoBaixa.data('id_recurso');
			console.log(codigoBaixa);
			$('[data-role=' + codigoBaixa+ ']').html('<span class="label label-danger">'+ a + '</span>');
		});
			
		
});
	
});

$(function() {
	$('.js-atualizar-status-conserto-recurso').on('click', function(event) {
		event.preventDefault();
		var botaoFuncionando= $(event.currentTarget);
		var urlFuncionando= botaoFuncionando.attr('href'); 
		console.log('urlFuncionando', urlFuncionando);
		
		var response = $.ajax({
			url: urlFuncionando, 
			type: 'PUT'
				
		});
		
		response.done(function(s) {
			var codigoFuncionando= botaoFuncionando.data('id_recurso');
			console.log(codigoFuncionando);
			$('[data-role=' + codigoFuncionando+ ']').html('<span class="label label-success">'+ s + '</span>');
		});
			
		
});
	
});

$('.disable-patrimonio-botao').on('click', function(event)
		{
	
	  if ($('.disable-patrimonio-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-patrimonio-input").attr("disabled", "disabled");
			$(".disable-patrimonio-input").attr("value", NULL);
	      }	
	  if (!$('.disable-patrimonio-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-patrimonio-input").removeAttr("disabled");
	      }
	});

$('.disable-computador-botao').on('click', function(event)
		{
	
	  if ($('.disable-computador-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-computador-input").attr("disabled", "disabled");
		  $(".disable-computador-input").prepend('<input type="hidden" name="computador_id_computador" value=""/>');
	      }	
	  if (!$('.disable-computador-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-computador-input").removeAttr("disabled");
	      }
	  var idcomputador = $(event.currentTarget);
	  console.log(idcomputador.data('id_computador'));
	  $('enviar').on('submit', function(event) 
		{
		  	
		})
	});
	
$('.disable-patrimonio-pc-botao').on('click', function(event)
		{
	
	  if ($('.disable-patrimonio-pc-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-patrimonio-pc-input").attr("disabled", "disabled");
			$(".disable-patrimonio-pc-input").attr("value", NULL);
	      }	
	  if (!$('.disable-patrimonio-pc-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-patrimonio-pc-input").removeAttr("disabled");
	      }
	});

$('.disable-usuario-pc-botao').on('click', function(event)
		{
	
	  if ($('.disable-usuario-pc-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-usuario-pc-input").attr("disabled", "disabled");
			$(".disable-usuario-pc-input").prepend('<input type="hidden" name="usuario_id_usuario" value=""/>');
	      }	
	  if (!$('.disable-usuario-pc-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-usuario-pc-input").removeAttr("disabled");
	      }
	});

$('.disable-patrimonio-impressora-botao').on('click', function(event)
		{
	
	  if ($('.disable-patrimonio-impressora-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-patrimonio-impressora-input").attr("disabled", "disabled");
			$(".disable-patrimonio-impressora-input").attr("value", NULL);
	      }	
	  if (!$('.disable-patrimonio-impressora-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-patrimonio-impressora-input").removeAttr("disabled");
	      }
	});

$('.disable-ip-pc-botao').on('click', function(event)
		{
	
	  if ($('.disable-ip-pc-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-ip-pc-input").attr("disabled", "disabled");
			$(".disable-ip-pc-input").attr("value", NULL);
	      }	
	  if (!$('.disable-ip-pc-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-ip-pc-input").removeAttr("disabled");
	      }
	});

$('.disable-ip-impressora-botao').on('click', function(event)
		{
	
	  if ($('.disable-ip-impressora-botao').is(":checked")) {
	        // Disable elements.
		  $(".disable-ip-impressora-input").attr("disabled", "disabled");
			$(".disable-ip-impressora-input").attr("value", NULL);
	      }	
	  if (!$('.disable-ip-impressora-botao').is(":checked")) {
	        // Enable elements.
	        $(".disable-ip-impressora-input").removeAttr("disabled");
	      }
	});

$('.testando-layer').on('click', function(event)
		{
			
			var informacao =  $(event.currentTarget);
			//console.log(informacao);
			var array = informacao.data('teste');
			console.log(array);
		
	
		})
