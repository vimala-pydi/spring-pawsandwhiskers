// Make sure to include jQuery library before this script

$(document).ready(function () {
    // Fetch cat and paw images from the servlet
    $.get('ImageDisplayServlet', function (catImages) {
      const catList = $('.catImageList');
      catImages.forEach(function (catImage) {
        const img = $('<img>').attr('src', catImage);
        catList.append(img);
      });
    });
  
    $.get('PawImageServlet', function (pawImages) {
      const pawList = $('.dogImageList');
      pawImages.forEach(function (pawImage) {
        const img = $('<img>').attr('src', pawImage);
        pawList.append(img);
      });
    });
  });
  