<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>Neuroteam</h2>
  <table border="1">
    <tr bgcolor="#9acd32">
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
      <td><xsl:value-of select="id"/></td>
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
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>
