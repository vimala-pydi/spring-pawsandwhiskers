// Make sure to include jQuery library before this script

$(document).ready(function () {
    // Fetch cat and paw images from the servlet
    $.get('CatImageServlet', function (catImages) {
      const catList = $('.cat-list');
      catImages.forEach(function (catImage) {
        const img = $('<img>').attr('src', catImage);
        catList.append(img);
      });
    });
  
    $.get('PawImageServlet', function (pawImages) {
      const pawList = $('.paw-list');
      pawImages.forEach(function (pawImage) {
        const img = $('<img>').attr('src', pawImage);
        pawList.append(img);
      });
    });
  });
  