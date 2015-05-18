#!/usr/bin/env bash

./stop-cells.sh

mvn -f ../gos-cell/pom.xml clean package

#row1
docker run -d --name a1 -h a1.cell.dev.gos -e GOS_INIT_STATUS="false" -p 8081:8080 cell
sleep 0.5
docker run -d --name a2 -h a2.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name a3 -h a3.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5
docker run -d --name a4 -h a4.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5

#row2
docker run -d --name b1 -h b1.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5
docker run -d --name b2 -h b2.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name b3 -h b3.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name b4 -h b4.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5

#row 3
docker run -d --name c1 -h c1.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5
docker run -d --name c2 -h c2.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name c3 -h c3.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name c4 -h c4.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5
 
# #row 4
docker run -d --name d1 -h d1.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name d2 -h d2.cell.dev.gos -e GOS_INIT_STATUS="true" cell
sleep 0.5
docker run -d --name d3 -h d3.cell.dev.gos -e GOS_INIT_STATUS="false" cell
sleep 0.5
docker run -d --name d4 -h d4.cell.dev.gos -e GOS_INIT_STATUS="false" cell
