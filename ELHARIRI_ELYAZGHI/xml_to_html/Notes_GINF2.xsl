<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <head>
            <title>
                Affichage Module
            </title>

        </head>
        <table   style="font-family: arial, sans-serif; border-collapse: collapse; width: 60%; border: 1px solid #bfbfbf;">
            <h3> Affichage du module
                <xsl:value-of select="/Students/Student/module/@code">

                </xsl:value-of>
            </h3>
            <thead>
                <tr>
                    <th style="padding:9px; border: 1px solid #bfbfbf; ">Nom complet</th>
                    <th style="padding:9px; border: 1px solid #bfbfbf; ">Code Apogee</th>
                    <th style="padding:9px; border: 1px solid #bfbfbf; "> Note du module </th>
                </tr>
            </thead>
            <tbody>

                <xsl:for-each select="/Students/Student">

                            <tr>
                                <td style="padding:9px; border: 1px solid #bfbfbf; ">
                                    <xsl:value-of select="@Student_Name">

                                    </xsl:value-of>
                                </td>
                                <td style="padding:9px; border: 1px solid #bfbfbf; ">
                                    <xsl:value-of select="@CNE">

                                    </xsl:value-of>
                                </td>
                                <td style="padding:9px; border: 1px solid #bfbfbf; ">
                                    <xsl:value-of select="module[@code='GINF31']">

                                    </xsl:value-of>
                                </td>

                            </tr>

                </xsl:for-each>


            </tbody>

        </table>


        <table border="1" style="border-collapse:collapse">
            <h3> Etudiant convoqué au rattrapage pour le module
                <xsl:value-of select="/Students/Student/module/@code">

                </xsl:value-of>
            </h3>
            <thead>
                <tr>
                    <th>Nom complet</th>
                    <th>Code Apogee</th>
                    <th> Note du module </th>
                </tr>
            </thead>
            <tbody>
                <xsl:for-each select="/Students/Student">
                    <xsl:choose>
                        <xsl:when test="module[@code='GINF31'] &lt;12" >
                            <tr>
                                <td ><xsl:value-of select="@Student_Name">

                                </xsl:value-of> </td>
                                <td ><xsl:value-of select="@CNE">

                                </xsl:value-of> </td>
                                <td bgcolor="#d9534f">
                                    Ratt
                                </td>
                            </tr>
                        </xsl:when>
                    </xsl:choose>
                </xsl:for-each>


            </tbody>

        </table>






















    </xsl:template>
</xsl:stylesheet>