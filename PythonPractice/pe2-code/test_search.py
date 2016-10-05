from problem import TravelProblem, CityMap, EightPuzzleProblem, misplaced_tiles_heuristic, NQueensProblem
from search import best_first_tree_search, best_first_graph_search, uninformed_tree_search, uninformed_graph_search
from search import uniform_cost_search, greedy_best_first_search, astar_search, breadth_first_search, depth_first_search

if __name__ == '__main__':
       
    print
    print '=' * 50
    print '=' * 50
    print "N QUEENS PROBLEM"
    print '=' * 50
    print '=' * 50
      
    nq = NQueensProblem(8)
	
    print
    print '-' * 24 + '1' + '-' * 25
    print "Running DEPTH-FIRST-TREE-SEARCH"
    print '-' * 50
    	
    dfts = depth_first_search(nq, search_type=uninformed_tree_search)
    
    print "Solution", dfts.solution()
	
    print
    print '-' * 24 + '2' + '-' * 25
    print "Running BREADTH-FIRST-TREE-SEARCH"
    print '-' * 50
    	
    bfts = breadth_first_search(nq, search_type=uninformed_tree_search)
    
    print "Solution", bfts.solution()
    
		
    print
    print '=' * 50
    print '=' * 50
    print "HW1 MAP Question 3,4,5"
    print '=' * 50
    print '=' * 50

	
    city_map = CityMap()
    
    city_map.add_road('F', 'S', 5)
    
    city_map.add_road('S', 'C', 6)
    city_map.add_one_way_road('S', 'A', 2)
    
    city_map.add_one_way_road('A', 'D', 3)
    city_map.add_road('A', 'B', 5)
    
    city_map.add_road('B', 'G', 7)
    
    city_map.add_one_way_road('C', 'D', 3)
    city_map.add_road('C', 'E', 4)
    
    city_map.add_road('D', 'E', 4)
    
    city_map.add_road('E', 'G', 3)
    
    def city_h(node):
        if node.state == 'A':
            return 5
        elif node.state == 'B':
            return 2
        elif node.state == 'C':
            return 7
        elif node.state == 'D':
            return 5
        elif node.state == 'E':
            return 2
        elif node.state == 'F':
            return 10
        elif node.state == 'G':
            return 0
        elif node.state == 'S':
            return 8
    
    travel_problem = TravelProblem('S', 'G', city_map)
    
        
    print
    print '-' * 50
    print "Running UNIFORM-COST-GRAPH-SEARCH"
    print '-' * 50
    
    ucs = uniform_cost_search(travel_problem, search_type=best_first_graph_search)
    
    print "Solution", ucs.solution()
    
    print
    print '-' * 50
    print "Running GREEDY-BEST-FIRST-TREE-SEARCH"
    print '-' * 50
    
    gbfs = greedy_best_first_search(travel_problem, city_h, search_type=best_first_tree_search)
    
    print "Solution", gbfs.solution()
    
       
    print
    print '-' * 50
    print "Running A*-TREE-SEARCH"
    print '-' * 50
    
    asts = astar_search(travel_problem, city_h, search_type=best_first_tree_search)
    
    print "Solution", asts.solution()
	
    print
    print '=' * 50
    print '=' * 50
    print "HW2 ROMANIA"
    print '=' * 50
    print '=' * 50

	
    city_map2 = CityMap()
    city_map2.add_road('AR', 'ZE', 75)
    city_map2.add_road('AR', 'SI', 140)
    city_map2.add_road('AR', 'TI', 118)
    city_map2.add_road('OR', 'ZE', 71)
    city_map2.add_road('OR', 'SI', 151)
    city_map2.add_road('SI', 'FA', 99)
    city_map2.add_road('SI', 'RI', 80)
    city_map2.add_road('RI', 'PI', 97)
    city_map2.add_road('RI', 'CR', 146)
    city_map2.add_road('CR', 'PI', 138)
    city_map2.add_road('TI', 'LU', 111)
    city_map2.add_road('LU', 'ME', 70)
    city_map2.add_road('ME', 'DR', 75)
    city_map2.add_road('DR', 'CR', 120)
    city_map2.add_road('PI', 'BU', 101)
    city_map2.add_road('FA', 'BU', 211)
    city_map2.add_road('BU', 'GI', 90)
    city_map2.add_road('BU', 'UR', 85)
    city_map2.add_road('UR', 'HI', 98)
    city_map2.add_road('HI', 'EF', 86)
    city_map2.add_road('UR', 'VA', 142)
    city_map2.add_road('VA', 'IA', 92)
    city_map2.add_road('IA', 'NE', 87)
    
    def city_h2(node):
        if node.state == 'AR':
            return 366
        elif node.state == 'BU':
            return 0
        elif node.state == 'CR':
            return 160
        elif node.state == 'DR':
            return 242
        elif node.state == 'EF':
            return 161
        elif node.state == 'FA':
            return 176
        elif node.state == 'GI':
            return 77
        elif node.state == 'HI':
            return 151
        elif node.state == 'IA':
            return 226
        elif node.state == 'LU':
            return 244
        elif node.state == 'ME':
            return 241
        elif node.state == 'NE':
            return 234
        elif node.state == 'OR':
            return 380
        elif node.state == 'PI':
            return 100
        elif node.state == 'RI':
            return 193
        elif node.state == 'SI':
            return 253
        elif node.state == 'TI':
            return 329
        elif node.state == 'UR':
            return 80
        elif node.state == 'VA':
            return 199
        elif node.state == 'ZE':
            return 374
    
    travel_problem2 = TravelProblem('AR', 'BU', city_map2)
    
        
    print
    print '-' * 50
    print "Running UNIFORM-COST-GRAPH-SEARCH"
    print '-' * 50
    
    ucs2 = uniform_cost_search(travel_problem2, search_type=best_first_graph_search)
    
    print "Solution", ucs2.solution()
    
    print
    print '-' * 50
    print "Running GREEDY-BEST-FIRST-TREE-SEARCH"
    print '-' * 50
    
    gbfs2 = greedy_best_first_search(travel_problem2, city_h2, search_type=best_first_tree_search)
    
    print "Solution", gbfs2.solution()
    
       
    print
    print '-' * 50
    print "Running A*-TREE-SEARCH"
    print '-' * 50
    
    asts2 = astar_search(travel_problem2, city_h2, search_type=best_first_tree_search)
    
    print "Solution", asts2.solution()
    
    
    
    
    
    
    
    
    
