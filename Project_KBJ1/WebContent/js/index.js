

$('.dropdown-toggle').on('click', function(e) {
  e.stopPropagation();
  e.preventDefault();

  var self = $(this);
  if(self.is('.disabled, :disabled')) {
    return false;
  }
  self.parent().toggleClass("open");
});
