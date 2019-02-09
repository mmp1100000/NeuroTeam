<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
    <head>
      <meta charset="UTF-8"/>
          <link rel="stylesheet" href="XMLSchema/css/style.css"/>
    </head>
  <body>
  <h3>Pacientes</h3>
  <table class="container">
    <tr>
      <th>id</th>
      <th>nombre</th>
      <th>apellidos</th>
      <th>nss</th>
      <th>genero</th>
      <th>altura</th>
      <th>peso</th>
      <th>fecha_nacimiento</th>
    </tr>
    <xsl:for-each select="neuroteam/paciente">
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td><xsl:value-of select="nombre"/></td>
      <td><xsl:value-of select="apellidos"/></td>
      <td><xsl:value-of select="nss"/></td>
      <td><xsl:value-of select="genero"/></td>
      <td><xsl:value-of select="altura"/></td>
      <td><xsl:value-of select="peso"/></td>
      <td><xsl:value-of select="fecha_nacimiento"/></td>
    </tr>
    </xsl:for-each>
  </table>

  <h3>Médicos</h3>
  <table  class="container">
    <tr>
      <th>id</th>
      <th>nombre</th>
      <th>apellidos</th>
      <th>licencia</th>
    </tr>
    <xsl:for-each select="neuroteam/Medico">
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td><xsl:value-of select="nombre"/></td>
      <td><xsl:value-of select="apellidos"/></td>
      <td><xsl:value-of select="licencia"/></td>
    </tr>
    </xsl:for-each>
  </table>

  <h3>Informes</h3>
  <table class="container">
    <tr>
      <th>id</th>
      <th>Sintomas</th>
      <th>Fecha Consulta</th>
      <th>Diagnostico</th>
      <th>Medico</th>
      <th>Paciente</th>
    </tr>
    <xsl:for-each select="neuroteam/informe">
    <tr>
      <td><xsl:value-of select="@id"/></td>
      <td><xsl:value-of select="sintomas"/></td>
      <td><xsl:value-of select="fecha"/></td>
      <td><xsl:value-of select="diagnostico"/></td>
      <td><xsl:value-of select="medico_id"/></td>
      <td><xsl:value-of select="paciente_id"/></td>
    </tr>
    </xsl:for-each>
  </table>

  <h3>Medico atiende a paciente</h3>
  <table class="container">
    <tr>
      <th colspan="3" align="center">Medico</th>
      <th colspan="3" align="center">Paciente</th>
    </tr>
    <tr>
      <th>Apellidos</th>
      <th>Nombre</th>
      <th>Licencia</th>
      <th>Apellidos</th>
      <th>Nombre</th>
      <th>nss</th>
    </tr>
    <xsl:for-each select="neuroteam/medico_has_paciente">
    <xsl:variable name="medico_id">
      <xsl:value-of select="@Medico_id"/>
    </xsl:variable>
    <xsl:variable name="paciente_id">
      <xsl:value-of select="@Paciente_id"/>
    </xsl:variable>
    <tr>
      <td><xsl:value-of select="/neuroteam/Medico[@id=$medico_id]/apellidos"/></td>
      <td><xsl:value-of select="/neuroteam/Medico[@id=$medico_id]/nombre"/></td>
      <td><xsl:value-of select="/neuroteam/Medico[@id=$medico_id]/licencia"/></td>
      <td><xsl:value-of select="/neuroteam/paciente[@id=$paciente_id]/apellidos"/></td>
      <td><xsl:value-of select="/neuroteam/paciente[@id=$paciente_id]/nombre"/></td>
      <td><xsl:value-of select="/neuroteam/paciente[@id=$paciente_id]/nss"/></td>
    </tr>
    </xsl:for-each>
  </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
