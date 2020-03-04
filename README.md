# bingo
Juego Familiar en donde tu celular es tu cartón.

## Vistas Principales

```
/display.xhtml : Tablero con los numeros jugados hasta el momento, la ídea es proyectar o mostrar en una TV (zoom por navegador)
/carton.xhtml : cartón de bingo destinado para visualazar en celular
/mensaje.xhtml : permite enviar un mensaje para visualizar en tablero (se llega a travez del cartón)
/numero.xhtml : permite obtener el próximo número desde el celular (se ve reflejado en tablero)
/admin.xhtml : muestra opciones de administración
/usuarios.xhtml : muestra los usuarios conectados (aún en desarrollo)
```

## Entorno de Desarrollo

Netbeans 11
java jdk8
TomEE (puede ser otro, ej: payara)
Framework: JSF - CDI + Primefaces 7.0 + Omnifaces 2.7.1
Se utiliza plugins maven para empaquetar todos en un jar

