function load_down_selection_rules
%% load_down_selection_rules.m
global params
r = global_jess_engine();
% if strcmp(params.MODE,'SELECTION') 
%     r.eval(['(bind ?down_selection_rules_clp "' params.down_selection_rules_selection_clp '")']);
% elseif strcmp(params.MODE,'PACKAGING')
%     r.eval(['(bind ?down_selection_rules_clp "' params.down_selection_rules_packaging_clp '")']);
% elseif strcmp(params.MODE,'SCHEDULING')
%     r.eval(['(bind ?down_selection_rules_clp "' params.down_selection_rules_scheduling_clp '")']);
% elseif strcmp(params.MODE,'ASSIGNING')
%     r.eval(['(bind ?down_selection_rules_clp "' params.down_selection_rules_assigning_clp '")']);
% end

r.eval(['(bind ?down_selection_rules_clp "' params.down_selection_rules_adhoc_clp '")']);
r.eval('(batch ?down_selection_rules_clp)');

end