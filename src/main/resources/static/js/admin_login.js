document.addEventListener('DOMContentLoaded', () => {
  const yeti = document.querySelector('.yeti');
  const user = document.querySelector('#username');
  const pass = document.querySelector('#password');
  const toggle = document.querySelector('.toggle-pass');

  if (!yeti || !user || !pass) return;

  // Yeti mira el caret en "Correo"
  function lookAtCaret(input){
    try{
      const len = input.value.length || 1;
      const pos = input.selectionStart ?? len;
      const ratio = Math.max(0, Math.min(1, pos / len));
      const dx = (ratio - .5) * 12;  // -6..6 px
      const dy = 0;
      yeti.style.setProperty('--look-x', dx + 'px');
      yeti.style.setProperty('--look-y', dy + 'px');
    }catch(e){
      yeti.style.setProperty('--look-x', '0px');
      yeti.style.setProperty('--look-y', '0px');
    }
  }
  ['input','keyup','click','focus'].forEach(ev =>
    user.addEventListener(ev, () => lookAtCaret(user))
  );

  // Se tapa los ojos al escribir la contraseña
  pass.addEventListener('focus', () => yeti.classList.add('is-shy'));
  pass.addEventListener('keydown', () => yeti.classList.add('is-shy'));
  pass.addEventListener('blur',  () => yeti.classList.remove('is-shy'));

  // Mostrar/ocultar contraseña (si tenés el botón en el HTML)
  if (toggle) {
    toggle.addEventListener('click', () => {
      const isText = pass.getAttribute('type') === 'text';
      pass.setAttribute('type', isText ? 'password' : 'text');
      pass.focus();
    });
  }
  if (toggle) {
  toggle.addEventListener('click', () => {
    const isText = pass.getAttribute('type') === 'text';
    pass.setAttribute('type', isText ? 'password' : 'text');
    toggle.classList.toggle('active', !isText); // <- pinta el icono cerrado/abierto
    pass.focus();
  });
}

});
