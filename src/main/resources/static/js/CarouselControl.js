const slider = document.querySelector('.center-column');

function activate(e) {
    const items = document.querySelectorAll('.item-carousel');
    e.target.matches('.next') && slider.append(items[0])
    e.target.matches('.prev') && slider.prepend(items[items.length-1]);
}

document.addEventListener('click',activate,false);