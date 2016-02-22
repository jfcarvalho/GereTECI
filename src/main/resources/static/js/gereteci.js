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
	
	  if ($('.disable-patrimonio-botao').length) {
	        // Disable elements.
		  $(".disable-patrimonio-input").attr("disabled", "disabled");
			$(".disable-patrimonio-input").attr("value", NULL);
	      }	
	  if ($('.disable-patrimonio-botao').length) {
	        // Enable elements.
	        $(".disable-patrimonio-botao").removeAttr("disabled");
	      }
	});

$('.disable-computador-botao').on('click', function(event)
		{
	
	  if ($('.disable-computador-botao').length) {
	        // Disable elements.
		  $(".disable-computador-input").attr("disabled", "disabled");
			$(".disable-computador-option").attr("value", NULL);
	      }	
	  if ($('.disable-patrimonio-botao').length) {
	        // Enable elements.
	        $(".disable-computador-input").removeAttr("disabled");
	      }
	});
		

