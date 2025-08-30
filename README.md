# Sistema de Gestión de Servicios de Cuidados de Mascotas

## Descripción
Sistema desarrollado en Java para modelar un sistema de gestión de servicios de 
cuidados de mascotas, en el cual se debe llevar un registro clientes y masctoas, 
gestionar citas para servicios como lo pueden ser pelqueria y veterinaria y 
llevar un manejo del hisotrial de servicios.

## Autor
- Sebastián Garay Núñez
- Ingeniería en Informática / Pontificia Universidad Católica de Valparaíso
- Benjamín Lazcano
- Ingeniería en Informática / Pontificia Universidad Católica de Valparaíso
- Vicente Olguin
- Ingeniería en Informática / Pontificia Universidad Católica de Valparaíso

## Tecnologías utilizadas
- Java
- NetBeans IDE
- GitHub

## Estructura del proyecto
- Clases principales: Veterinaria, Cliente, Mascota, Servicio
- Funcionalidades: Agregar (Cliente / Mascota / Servicio), Mostrar (C / M / S)

## Instrucciones de uso
- Instalación:
1. Abrir el proyecto en NetBeans
2. Compilar
3. Ejecutar el proyecto
- Uso del Sistema:
0. El sistema cuenta con datos precargados
- Funciones disponibles:
1. Agregar cliente 
a) Ingresar nombre, rut, telefono y direccion 
b) El sistema verifica que el rut no este duplicado
2. Agregar mascota a cliente 
a) Ingresar rut del dueño 
b) Si existe, permite agregar nombre, tipo, raza y edad de la mascota
3. Agregar servicio a masctoa 
a) Buscar cliente por rut 
b) Buscar mascota por nombre 
c) Agregar servicio con tipo, fecha, descripción, precio y estado
4. Listar clientes y mascotas a) Muestra toda la información de forma jerárquica

## Fecha de desarrollo
Versión (Estable): 29/08/2025