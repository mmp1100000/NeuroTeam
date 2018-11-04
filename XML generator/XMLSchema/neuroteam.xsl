<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Neuroteam</h2>

  <h3>Pacientes</h3>
  <table border="1">
    <tr bgcolor="#32cdcd">
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
  <table border="1">
    <tr bgcolor="#32cdcd">
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
  <table border="1">
    <tr bgcolor="#32cdcd">
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
  <table border="1">
    <tr bgcolor="#32cdcd">
      <th>Medico</th>
      <th>Paciente</th>
    </tr>
    <xsl:for-each select="neuroteam/medico_has_paciente">
    <tr>
      <td><xsl:value-of select="../Medico/nombre"/></td>
      <td><xsl:value-of select="medico_id"/></td>
    </tr>
    </xsl:for-each>
  </table>

  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
