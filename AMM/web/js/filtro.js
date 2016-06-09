/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function filtra(event){
    var stringa=document.getElementById("txt_filtro").value;
    $.ajax({
        url: 'filter.json',
        data:{
            q:stringa
        },
        dataType:'json',
        success:function(data,state){
            popola_tabella(data);
        },
        error:function(data,state){
            popola_tabella();
        }
    });
    
}

function popola_tabella(data){
    var table=$('#tabella');
    var col_names=Array('Copertina','Nome','Pezzi disponibili','Prezzo','Carrello');
    var col_id=Array('copertina','nome','num_pezzi','prezzo','carrello');
    table.empty();
    var tr=document.createElement('tr');
        for(var i=0;i<col_names.length;i++){
            var th=document.createElement('th');
            th.appendChild(document.createTextNode(col_names[i]));
            th.setAttribute('id',col_id[i]);
            tr.appendChild(th);
        }       
    table.append(tr);
    
    if(data!==undefined&&data.length>0){
        i=1;
        for(var articolo in data){
            
            tr=document.createElement('tr');
            if(i%2==0)
                tr.setAttribute('class','pari');
            
            i++;
            var td=document.createElement('td');
            var image=document.createElement('img');
            image.setAttribute('src',data[articolo].copertina);
            image.setAttribute('alt',data[articolo].titolo);
            image.setAttribute('width','30px');
            image.setAttribute('height','50px');
            td.appendChild(image);
            tr.appendChild(td);

            td=document.createElement('td');
            td.innerText=data[articolo].titolo;
            tr.appendChild(td);

            td=document.createElement('td');
            td.innerText=data[articolo].numero;
            td.setAttribute('class','col_pezzi');
            tr.appendChild(td);

            td=document.createElement('td');
            td.innerText=data[articolo].prezzo;
            td.setAttribute('class','col_prezzi');
            tr.appendChild(td);

            td=document.createElement('td');
            var link=document.createElement('a');
            link.setAttribute('href','cliente.html?id='+data[articolo].id);
            link.innerText='Aggiungi al carrello';
            td.appendChild(link);
            tr.appendChild(td);
            
            table.append(tr);
        } 
    }else{
        var tr=document.createElement('tr');
        var td=document.createElement('td');
        td.setAttribute('colspan','5');
        td.innerHTML='<h3>Nessun articolo trovato</h3>';
        tr.appendChild(td);
        table.append(tr);
    }
    
}

